package com.rentacar.authentificationservice.services;

import com.rentacar.authentificationservice.dto.request.*;
import com.rentacar.authentificationservice.dto.response.UserResponseDTO;

public interface IAuthenticationService {

    void registerSimpleUser(SimpleUserDetailsDTO request);
    void registerAdmin(AdminDetailsDTO request);
    void registerAgent(AgentDetailsDTO request);
    UserResponseDTO login(LoginCredentialsDTO request);
    void changePassword(ChangePasswordDTO request);
    void banUser(ChangePasswordDTO request);
    void updateSimpleUser(UpdateUserRequestDTO request);
    void updateAgent(UpdateAgentRequestDTO request);
    void updateAdmin(UpdateAdminRequestDTO request);
    void deleteUser(ChangePasswordDTO request);

}
