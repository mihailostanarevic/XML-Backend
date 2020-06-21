package com.rentacar.authentificationservice.services;

import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    List<UserResponse> getAllUsers();

    User getUser(UUID id);

    List<UserResponse> getCustomers();

}
