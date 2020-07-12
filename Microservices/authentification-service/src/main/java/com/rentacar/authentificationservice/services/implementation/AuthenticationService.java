package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.CoreAPI.dto.Role;
import com.rentacar.CoreAPI.dto.RoleList;
import com.rentacar.authentificationservice.dto.request.*;
import com.rentacar.authentificationservice.dto.response.StringResponse;
import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.entity.*;
import com.rentacar.authentificationservice.repository.*;
import com.rentacar.authentificationservice.security.TokenUtils;
import com.rentacar.authentificationservice.services.IAuthenticationService;
import com.rentacar.authentificationservice.util.enums.GeneralException;
import com.rentacar.authentificationservice.util.enums.RequestStatus;
import com.rentacar.authentificationservice.util.enums.UserRole;
import com.rentacar.authentificationservice.util.rabbit.QueueProducer;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final AuthenticationManager _authenticationManager;
    private final TokenUtils _tokenUtils;
    private final PasswordEncoder _passwordEncoder;
    private final IUserRepository _userRepository;
    private final IAgentRepository _agentRepository;
    private final IAdminRepository _adminRepository;
    private final ISimpleUserRepository _simpleUserRepository;
    private final IAuthorityRepository _authorityRepository;
    private final ILoginAttemptsRepository _loginAttemptsRepository;
    private final Logger logger = LoggerFactory.getLogger("Auth service app: " + AuthenticationService.class);

    @Autowired
    private QueueProducer queueProducer;

    public AuthenticationService(AuthenticationManager authenticationManager, TokenUtils tokenUtils, PasswordEncoder passwordEncoder, IUserRepository userRepository, IAgentRepository agentRepository, IAdminRepository adminRepository, ISimpleUserRepository simpleUserRepository, IAuthorityRepository authorityRepository, ILoginAttemptsRepository loginAttemptsRepository) {
        _authenticationManager = authenticationManager;
        _tokenUtils = tokenUtils;
        _passwordEncoder = passwordEncoder;
        _userRepository = userRepository;
        _agentRepository = agentRepository;
        _adminRepository = adminRepository;
        _simpleUserRepository = simpleUserRepository;
        _authorityRepository = authorityRepository;
        _loginAttemptsRepository = loginAttemptsRepository;
    }

    @Transactional(dontRollbackOn = GeneralException.class)
    @Override
    public UserResponse login(LoginCredentialsDTO request, HttpServletRequest httpServletRequest) throws GeneralException {
        LoginAttempts la = _loginAttemptsRepository.findOneByIpAddress(httpServletRequest.getRemoteAddr());
        if(la != null && Integer.parseInt(la.getAttempts()) >= 3 && la.getFirstMistakeDateTime().plusHours(12L).isAfter(LocalDateTime.now())){
            throw new GeneralException("You have reached your logging limit, please try again later.", HttpStatus.CONFLICT);
        }
        logger.trace("Username: " + request.getUsername() + " Password: " + _passwordEncoder.encode(request.getPassword()));
        long startTime = System.nanoTime();
        User user = _userRepository.findOneByUsername(request.getUsername());

        if(user == null || !_passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            if(la == null){
                LoginAttempts loginAttempts = new LoginAttempts();
                loginAttempts.setIpAddress(httpServletRequest.getRemoteAddr());
                loginAttempts.setAttempts("1");
                loginAttempts.setFirstMistakeDateTime(LocalDateTime.now());
                LoginAttempts saved = _loginAttemptsRepository.save(loginAttempts);
                System.out.println(saved.getId());
                throw new GeneralException("Bad credentials.", HttpStatus.BAD_REQUEST);
            }
            if(la.getFirstMistakeDateTime().plusHours(12L).isBefore(LocalDateTime.now())){
                la.setFirstMistakeDateTime(LocalDateTime.now());
                la.setAttempts("0");
            }
            int attempts = Integer.parseInt(la.getAttempts());
            attempts++;
            la.setAttempts(String.valueOf(attempts));
            _loginAttemptsRepository.save(la);
//            UserResponse userResponse = new UserResponse();
//            return userResponse;
            throw new GeneralException("Bad credentials.", HttpStatus.BAD_REQUEST);
//            throw new GeneralException("Unknown user", HttpStatus.BAD_REQUEST);
        }
        if(user.getSimpleUser() != null && user.getSimpleUser().getRequestStatus().equals(RequestStatus.PENDING)){
            throw new GeneralException("Your registration hasn't been approved yet.", HttpStatus.BAD_REQUEST);
        }

        if(user.getSimpleUser() != null && user.getSimpleUser().getRequestStatus().equals(RequestStatus.DENIED)){
            throw new GeneralException("Your registration has been denied.", HttpStatus.BAD_REQUEST);
        }

        if(user.getSimpleUser() != null && user.getSimpleUser().getRequestStatus().equals(RequestStatus.CONFIRMED)){
            throw new GeneralException("Your registration has been approved by admin. Please activate your account.", HttpStatus.BAD_REQUEST);
        }
        String mail = request.getUsername();
        String password = request.getPassword();
        Authentication authentication = null;
        try {
            authentication = _authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        }catch (BadCredentialsException e){
            logger.debug("User has entered bad login credentials");
            throw new GeneralException("Bad credentials.", HttpStatus.BAD_REQUEST);
        }catch (DisabledException e){
            logger.debug("User with id: " + user.getId() + " still has a pending status on his account");
            throw new GeneralException("Your registration request hasn't been approved yet.", HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            logger.debug("Some other exception");
            e.printStackTrace();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = "";
        int expiresIn = 0;
        if(!user.isHasSignedIn()){
            UserDetailsImpl userLog = (UserDetailsImpl) authentication.getPrincipal();
            jwt = _tokenUtils.generateToken(userLog.getUsername());
            expiresIn = _tokenUtils.getExpiredIn();
        }
        UserResponse userResponse = mapUserToUserResponse(user);
        userResponse.setToken(jwt);
        userResponse.setTokenExpiresIn(expiresIn);

        long endTime = System.nanoTime();
        double time = (double) ((endTime - startTime) / 1000000);
        logger.trace("Total time to login user " + userResponse.getUsername() +  " was " + time + " ms");
        logger.info(user.getUsername() + " has logged in");
        logger.warn("Test warning");
        logger.error("Test error to show that this mail is actually sent");
        return userResponse;


//        String mail = request.getUsername();
//        String password = request.getPassword();
//        Authentication authentication = null;
//        try {
//            authentication = _authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
//        }catch (BadCredentialsException e){
//            throw new GeneralException("Bad credentials.", HttpStatus.BAD_REQUEST);
//        }catch (DisabledException e){
//            throw new GeneralException("Your registration request hasn't been approved yet.", HttpStatus.BAD_REQUEST);
//        }catch (Exception e) {
//            System.out.println("Neki drugi exception [Exception]");
//            e.printStackTrace();
//        }
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = "";
//        int expiresIn = 0;
//        if(!user.isHasSignedIn()){
//            assert authentication != null;
//            UserDetailsImpl userLog = (UserDetailsImpl) authentication.getPrincipal();
//            jwt = _tokenUtils.generateToken(userLog.getUsername());
//            expiresIn = _tokenUtils.getExpiredIn();
//        }
//        UserResponse userResponse = mapUserToUserResponse(user);
//        userResponse.setToken(jwt);
//        userResponse.setTokenExpiresIn(expiresIn);
//
//        return userResponse;
    }

    @Override
    public void changePassword(ChangePasswordDTO request) {

    }

    @Override
    public void banUser(ChangePasswordDTO request) {

    }

    @Override
    public void updateSimpleUser(UpdateUserRequestDTO request) {

    }

    @Override
    public void updateAgent(UpdateAgentRequestDTO request) {

    }

    @Override
    public void updateAdmin(UpdateAdminRequestDTO request) {

    }

    @Override
    public void deleteUser(ChangePasswordDTO request) {

    }

    @Override
    public UserResponse createAgent(CreateAgentRequest request) throws GeneralException {
        if(!request.getPassword().equals(request.getRePassword())){
            logger.debug(request.getUsername() + " didn't match his/hers passwords");
            throw new GeneralException("Passwords don't match.", HttpStatus.BAD_REQUEST);
        }
//        checkSQLInjection(request);
        User user = new User();
        Agent agent = new Agent();
        user.setDeleted(false);
        user.setHasSignedIn(false);
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        List<Authority> authorities = new ArrayList<>();
        authorities.add(_authorityRepository.findByName("ROLE_SIMPLE_USER"));
        authorities.add(_authorityRepository.findByName("ROLE_AD_USER"));
        authorities.add(_authorityRepository.findByName("ROLE_MESSAGE_USER"));
        authorities.add(_authorityRepository.findByName("ROLE_AGENT"));
        authorities.add(_authorityRepository.findByName("ROLE_COMMENT_USER"));
        user.setAuthorities(new HashSet<>(authorities));
        user.setUserRole(UserRole.AGENT);
        agent.setName(request.getName());
        agent.setBankAccountNumber(request.getBankAccountNumber());
        agent.setDateFounded(request.getDateFounded());
        agent.setTin(request.getTin());
        Agent savedAgent = _agentRepository.save(agent);
        savedAgent.setUser(user);
        user.setAgent(savedAgent);
        User savedUser = _userRepository.save(user);

        System.out.println("Slanje emaila...");

        MailDTO mail = new MailDTO();
        mail.setId(savedAgent.getId());
        mail.setUsername(savedAgent.getUser().getUsername());
        mail.setRole("Agent");
        mail.setName(savedAgent.getName());
        try {
            queueProducer.produce(mail);
        } catch (Exception e) {
            System.out.println("Nisam poslao agent mail");
            e.printStackTrace();
        }

        logger.info(user.getUsername() + " account has been successfully created as an agent");
        return mapUserToUserResponse(savedUser);
    }

    @Override
    public UserResponse createSimpleUser(CreateSimpleUserRequest request) throws GeneralException {
        if(!request.getPassword().equals(request.getRePassword())){
            logger.debug(request.getUsername() + " didn't match his passwords");
            throw new GeneralException("Passwords don't match.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        SimpleUser simpleUser = new SimpleUser();
        user.setDeleted(false);
        user.setHasSignedIn(false);
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        List<Authority> authorities = new ArrayList<>();
        authorities.add(_authorityRepository.findByName("ROLE_SIMPLE_USER"));
        authorities.add(_authorityRepository.findByName("ROLE_RENT_USER"));
        authorities.add(_authorityRepository.findByName("ROLE_REQUEST"));       // treba da se dodaje kada se rentira
        authorities.add(_authorityRepository.findByName("ROLE_COMMENT_USER"));  // treba da se dodaje kada se rentira
        authorities.add(_authorityRepository.findByName("ROLE_MESSAGE_USER"));  // treba da se dodaje kada se rentira
        authorities.add(_authorityRepository.findByName("ROLE_REVIEWER_USER")); // treba da se dodaje kada se rentira
        authorities.add(_authorityRepository.findByName("ROLE_AD_USER"));       // samo zbog toga sto moze da postavlja oglas
        user.setAuthorities(new HashSet<>(authorities));
        user.setUserRole(UserRole.SIMPLE_USER);
        simpleUser.setAddress(request.getCountry()+", "+request.getCity()+","+request.getAddress());
        simpleUser.setFirstName(request.getFirstName());
        simpleUser.setLastName(request.getLastName());
        simpleUser.setSsn(request.getSsn());
        simpleUser.setRequestStatus(RequestStatus.PENDING);
        SimpleUser savedSimpleUser = _simpleUserRepository.save(simpleUser);
        savedSimpleUser.setUser(user);
        user.setSimpleUser(savedSimpleUser);
        User savedUser = _userRepository.save(user);

        // brisati ovo za agenta
//        Agent agent = new Agent();
//        agent.setUser(simpleUser.getUser());
//        agent.setAddress(simpleUser.getAddress());
//        agent.setBankAccountNumber("none");
//        agent.setName(simpleUser.getFirstName() + " " + simpleUser.getLastName());
//        agent.setSimpleUserId(simpleUser.getId());
//        agent.setTin(simpleUser.getSsn());
//        _agentRepository.save(agent);

        logger.info(user.getUsername() + " account has been successfully created as a simple user");
        return mapUserToUserResponse(savedUser);
    }

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        if(user.getSimpleUser() != null){
            userResponse.setId(user.getSimpleUser().getId());
        }else if(user.getAgent() != null){
            userResponse.setId(user.getAgent().getId());
        }else if(user.getAdmin() != null){
            userResponse.setId(user.getAdmin().getId());
        }
        userResponse.setUsername(user.getUsername());
        if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_ADMIN"))){
            userResponse.setUserRole("ADMIN_ROLE");
        }else if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_AGENT"))){
            userResponse.setUserRole("AGENT_ROLE");
        }else if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_SIMPLE_USER"))){
            userResponse.setUserRole("SIMPLE_USER_ROLE");
        }
        return userResponse;
    }

    @Override
    public UserResponse setNewPassword(UUID id, NewPassordRequest request) throws GeneralException {
        if (!request.getPassword().equals(request.getRePassword())) {
            logger.debug("User: " + id + "didn't match his passwords when trying to change password");
            throw new GeneralException("Passwords don't match", HttpStatus.BAD_REQUEST);
        }

        Admin admin = _adminRepository.findOneById(id);
        Agent agent = _agentRepository.findOneById(id);
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);

        User user = null;

        if(admin != null){
            user = admin.getUser();
        }else if(agent != null){
            user = agent.getUser();
        }else if(simpleUser != null) {
            user = simpleUser.getUser();
        }

        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        logger.info(user.getUsername() + " has changed his password");

        if(!user.isHasSignedIn()){
            user.setHasSignedIn(true);
        }

        if(admin != null){
            _adminRepository.save(admin);
        }

        return mapUserToUserResponse(user);
    }

    @Override
    public void confirmRegistrationRequest(GetIdRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        simpleUser.setRequestStatus(RequestStatus.CONFIRMED);
        LocalDateTime currentTime = LocalDateTime.now();
        simpleUser.setConfirmationTime(currentTime);
        _simpleUserRepository.save(simpleUser);

        System.out.println("Slanje emaila...");

        MailDTO mail = new MailDTO();
        mail.setId(simpleUser.getId());
        mail.setRole("Simple");
        mail.setFirstName(simpleUser.getFirstName());
        mail.setLastName(simpleUser.getLastName());
        mail.setUsername(simpleUser.getUser().getUsername());
        try {
            queueProducer.produce(mail);
        } catch (Exception e) {
            System.out.println("Nisam poslao simple user registration mail");
            e.printStackTrace();
        }
    }

    @Override
    public void approveRegistrationRequest(GetIdRequest request) throws GeneralException {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        if(simpleUser.getConfirmationTime().plusHours(12L).isBefore(LocalDateTime.now())){
            simpleUser.setRequestStatus(RequestStatus.DENIED);
            _simpleUserRepository.save(simpleUser);
            logger.debug("The activational link has expired");
            throw new GeneralException("Your activation link has expired.", HttpStatus.BAD_REQUEST);
        }
        simpleUser.setRequestStatus(RequestStatus.APPROVED);

        // kreiranje agenta (da bi mogao kreirati oglase)
        Agent agent = new Agent();
        agent.setUser(simpleUser.getUser());
        agent.setAddress(simpleUser.getAddress());
        agent.setBankAccountNumber("none");
        agent.setName(simpleUser.getFirstName() + " " + simpleUser.getLastName());
        agent.setSimpleUserId(simpleUser.getId());
        agent.setTin(simpleUser.getSsn());
        _agentRepository.save(agent);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public void denyRegistrationRequest(GetIdRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        simpleUser.setRequestStatus(RequestStatus.DENIED);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public String getPermission(String token) {
        String username = _tokenUtils.getUsernameFromToken(token);
        User user = _userRepository.findOneByUsername(username);
        String retVal = "";
        for (GrantedAuthority authority : user.getAuthorities()) {
            retVal += authority.getAuthority()+",";
        }
        return retVal.substring(0,retVal.length()-1);
    }

    @Override
    public StringResponse limitRedirect(HttpServletRequest request) {
        LocalDateTime now = LocalDateTime.now();
        StringResponse response = new StringResponse();
        LoginAttempts loginAttempts = _loginAttemptsRepository.findOneByIpAddress(request.getRemoteAddr());
        if(loginAttempts != null){
            if(loginAttempts.getAttempts().equals("3") && loginAttempts.getFirstMistakeDateTime().plusHours(12L).isAfter(now)){
                response.setMessage("LIMIT");
                return response;
            }
        }
        return response;
    }

    @Override
    public List<UserResponse> getAllRegistrationRequests() throws Exception {
        List<User> users = _userRepository.findAllByDeleted(false);
        List<User> requests = new ArrayList<>();
        for (User u: users){
            if(u.getRoles().contains(_authorityRepository.findByName("ROLE_SIMPLE_USER"))){
                SimpleUser simpleUser = _simpleUserRepository.findOneByUser(u);
                if(simpleUser != null && simpleUser.getRequestStatus().equals(RequestStatus.PENDING)){
                    requests.add(u);
                }
            }
        }
        return requests.stream()
                .map(user -> mapUserToUserResponse(user))
                .collect(Collectors.toList());
    }

    @Override
    public void addUserRole(UUID simpleUserId, RoleList roleList) throws NotFoundException {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(simpleUserId);
        if(simpleUser == null) {
            throw new NotFoundException("Simple user not found!");
        }
        addRolesToSimpleUser(simpleUser, roleList);
    }

    private void addRolesToSimpleUser(SimpleUser simpleUser, RoleList roleList) {
        User user = simpleUser.getUser();
        for (Role role : roleList.getRoleList()) {
            user.getRoles().add(_authorityRepository.findByName(role.getRole()));
        }
        _userRepository.save(user);
    }

}
