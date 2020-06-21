package com.rentacar.authentificationservice.controller;

import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.dto.feignClient.UserDTO;
import com.rentacar.authentificationservice.dto.feignClient.UserMessageDTO;
import com.rentacar.authentificationservice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

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
  
    @GetMapping("/{id}")
    public UserMessageDTO getUser(@PathVariable("id") UUID id){
        return _userService.getUser(id);
    }

}
