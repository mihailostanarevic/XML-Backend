package com.rentacar.authentificationservice.controller;

import com.rentacar.authentificationservice.dto.feignClient.UserMessageDTO;
import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.services.IUserService;
import com.rentacar.authentificationservice.util.enums.RequestStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/auth/users")
public class UserController {

    private final IUserService _userService;

    public UserController(IUserService userService) {
        this._userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return _userService.getAllUsers();
    }

    @GetMapping("/customer")
    public ResponseEntity<List<UserResponse>> getCustomers() {
        return new ResponseEntity<>(_userService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserMessageDTO getUser(@PathVariable("id") UUID id){
        return _userService.getUser(id);
    }

    GetMapping("/{id}/requests")
    public List<UsersAdsResponse> usersAdsFromStatus(@PathVariable("id") UUID userId, @RequestParam("status") String string){
        String stringStatus = string.toUpperCase();
        RequestStatus status;
        switch (stringStatus){
            case "PAID" : status = RequestStatus.PAID;
                break;
            case "CANCELED" : status = RequestStatus.CANCELED;
                break;
            case "CONFIRMED" : status = RequestStatus.CONFIRMED;
                break;
            case "DENIED" : status = RequestStatus.DENIED;
                break;
            case "APPROVED" : status = RequestStatus.APPROVED;
                break;
            case "RESERVED" : status = RequestStatus.RESERVED;
                break;
            default: status = RequestStatus.PENDING;
        }

        return _userService.getUsersRequestFromStatus(userId,status);
    }

}
