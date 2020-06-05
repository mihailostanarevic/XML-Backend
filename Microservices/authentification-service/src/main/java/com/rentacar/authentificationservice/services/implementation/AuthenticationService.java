package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.dto.request.*;
import com.rentacar.authentificationservice.dto.response.UserResponseDTO;
import com.rentacar.authentificationservice.entity.User;
import com.rentacar.authentificationservice.entity.UserDetailsImpl;
import com.rentacar.authentificationservice.repository.IAuthorityRepository;
import com.rentacar.authentificationservice.repository.IUserRepository;
import com.rentacar.authentificationservice.security.TokenUtils;
import com.rentacar.authentificationservice.services.IAuthenticationService;
import com.rentacar.authentificationservice.util.enums.GeneralException;
import com.rentacar.authentificationservice.util.enums.RequestStatus;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final AuthenticationManager _authenticationManager;
    private final TokenUtils _tokenUtils;
    private final PasswordEncoder _passwordEncoder;
    private final IUserRepository _userRepository;
    private final IAuthorityRepository _authorityRepository;

    public AuthenticationService(AuthenticationManager authenticationManager, TokenUtils tokenUtils, PasswordEncoder passwordEncoder, IUserRepository userRepository, IAuthorityRepository authorityRepository) {
        _authenticationManager = authenticationManager;
        _tokenUtils = tokenUtils;
        _passwordEncoder = passwordEncoder;
        _userRepository = userRepository;
        _authorityRepository = authorityRepository;
    }

    @Override
    public void registerSimpleUser(SimpleUserDetailsDTO request) {

    }

    @Override
    public void registerAdmin(AdminDetailsDTO request) {

    }

    @Override
    public void registerAgent(AgentDetailsDTO request) {

    }

    @Override
    public UserResponseDTO login(LoginCredentialsDTO request) throws GeneralException {
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

        String jwt = "";
        int expiresIn = 0;
        if(!user.isHasSignedIn()){
            assert authentication != null;
            UserDetailsImpl userLog = (UserDetailsImpl) authentication.getPrincipal();
            jwt = _tokenUtils.generateToken(userLog.getUsername());
            expiresIn = _tokenUtils.getExpiredIn();
        }
        UserResponseDTO userResponse = mapUserToUserResponse(user);
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

    private UserResponseDTO mapUserToUserResponse(User user) {
        UserResponseDTO userResponse = new UserResponseDTO();
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

}
