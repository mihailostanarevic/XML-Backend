package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.dto.request.*;
import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.entity.*;
import com.rentacar.authentificationservice.repository.*;
import com.rentacar.authentificationservice.security.TokenUtils;
import com.rentacar.authentificationservice.services.IAuthenticationService;
import com.rentacar.authentificationservice.util.enums.GeneralException;
import com.rentacar.authentificationservice.util.enums.RequestStatus;
import com.rentacar.authentificationservice.util.enums.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

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

    public AuthenticationService(AuthenticationManager authenticationManager, TokenUtils tokenUtils, PasswordEncoder passwordEncoder, IUserRepository userRepository, IAgentRepository agentRepository, IAdminRepository adminRepository, ISimpleUserRepository simpleUserRepository, IAuthorityRepository authorityRepository) {
        _authenticationManager = authenticationManager;
        _tokenUtils = tokenUtils;
        _passwordEncoder = passwordEncoder;
        _userRepository = userRepository;
        _agentRepository = agentRepository;
        _adminRepository = adminRepository;
        _simpleUserRepository = simpleUserRepository;
        _authorityRepository = authorityRepository;
    }

    @Override
    public UserResponse login(LoginCredentialsDTO request) throws GeneralException {
        User user = _userRepository.findOneByUsername(request.getUsername());

        String mail = request.getUsername();
        String password = request.getPassword();
        Authentication authentication = null;
        try {
            authentication = _authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        }catch (BadCredentialsException e){
            throw new GeneralException("Bad credentials.", HttpStatus.BAD_REQUEST);
        }catch (DisabledException e){
            throw new GeneralException("Your registration request hasn't been approved yet.", HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            System.out.println("Neki drugi exception [Exception]");
            e.printStackTrace();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = "";
        int expiresIn = 0;
        if(!user.isHasSignedIn()){
            assert authentication != null;
            UserDetailsImpl userLog = (UserDetailsImpl) authentication.getPrincipal();
            jwt = _tokenUtils.generateToken(userLog.getUsername());
            expiresIn = _tokenUtils.getExpiredIn();
        }
        UserResponse userResponse = mapUserToUserResponse(user);
        userResponse.setToken(jwt);
        userResponse.setTokenExpiresIn(expiresIn);

        return userResponse;
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
//            logger.info(request.getUsername() + " didn't match his/hers passwords");
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
        user.setAuthorities(new HashSet<>(authorities));
        user.setUserRole(UserRole.AGENT);
        agent.setName(request.getName());
        agent.setBankAccountNumber(request.getBankAccountNumber());
        agent.setDateFounded(request.getDateFounded());
        agent.setTin(request.getTin());
//        agent.setRequestStatus(RequestStatus.PENDING);
        Agent savedAgent = _agentRepository.save(agent);
        savedAgent.setUser(user);
        user.setAgent(savedAgent);
        User savedUser = _userRepository.save(user);

//        logger.info(user.getUsername() + " account has been successfully created as an agent");
        return mapUserToUserResponse(savedUser);
    }

    @Override
    public UserResponse createSimpleUser(CreateSimpleUserRequest request) throws GeneralException {
        if(!request.getPassword().equals(request.getRePassword())){
//            logger.info(request.getUsername() + " didn't match his passwords");
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

//        logger.info(user.getUsername() + " account has been successfully created as a simple user");
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
//            logger.info("User didn't match his passwords when trying to change password");
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
//        logger.info(user.getUsername() + " has changed his password");

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
//        _emailService.approveRegistrationMail(simpleUser);
    }

    @Override
    public void approveRegistrationRequest(GetIdRequest request) throws GeneralException {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        if(simpleUser.getConfirmationTime().plusHours(12L).isBefore(LocalDateTime.now())){
            simpleUser.setRequestStatus(RequestStatus.DENIED);
            _simpleUserRepository.save(simpleUser);
            throw new GeneralException("Your activation link has expired.", HttpStatus.BAD_REQUEST);
        }
        simpleUser.setRequestStatus(RequestStatus.APPROVED);
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

}
