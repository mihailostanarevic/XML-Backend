package com.rentacar.authentificationservice.controller;


import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.services.IUserService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class UserController {

    private final IUserService _userService;

    public UserController(IUserService userService) {
        _userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() throws Exception{
        return _userService.getAllUsers();
    }

    @GetMapping("/customer")
    public List<UserResponse> getCustomers() {
        return _userService.getCustomers();
    }

}
