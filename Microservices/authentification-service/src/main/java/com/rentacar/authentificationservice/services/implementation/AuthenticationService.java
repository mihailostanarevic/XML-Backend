package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.dto.request.*;
import com.rentacar.authentificationservice.services.IAuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

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
    public String login(LoginCredentialsDTO request) {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
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
}
