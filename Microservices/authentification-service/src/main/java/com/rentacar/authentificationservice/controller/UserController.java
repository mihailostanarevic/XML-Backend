package com.rentacar.authentificationservice.controller;

import com.rentacar.authentificationservice.dto.feignClient.UserDTO;
import com.rentacar.authentificationservice.dto.feignClient.UserMessageDTO;
import com.rentacar.authentificationservice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private IUserService _userService;

    @GetMapping("/{id}")
    public UserMessageDTO getUser(@PathVariable("id") UUID id){
        return _userService.getUser(id);
    }
}
