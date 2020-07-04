package com.rentacar.authentificationservice.controller;

import com.rentacar.authentificationservice.dto.response.RoleResponse;
import com.rentacar.authentificationservice.dto.feignClient.UserMessageDTO;
import com.rentacar.authentificationservice.dto.response.UserDetailsResponse;
import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.security.TokenUtils;
import com.rentacar.authentificationservice.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/auth/users")
public class UserController {

    private final IUserService _userService;
    private final TokenUtils _tokenUtils;

    public UserController(IUserService userService, TokenUtils tokenUtils) {
        this._userService = userService;
        _tokenUtils = tokenUtils;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return _userService.getAllUsers();
    }

    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('CREATE_REQUEST')")
    public ResponseEntity<List<UserResponse>> getCustomers() {
        return new ResponseEntity<>(_userService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('CHANGE_PERMISSION')")
    public ResponseEntity<List<UserDetailsResponse>> getUsers() {
        return new ResponseEntity<>(_userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}/permissions")
    @PreAuthorize("hasAuthority('CHANGE_PERMISSION')")
    public ResponseEntity<List<RoleResponse>> getPermissions(@PathVariable("id") UUID userId) {
        return new ResponseEntity<>(_userService.getPermissions(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    @PreAuthorize("hasAuthority('CHANGE_PERMISSION')")
    public ResponseEntity<List<UserDetailsResponse>> deleteRole(@PathVariable("userId") UUID userId,
                                                                @PathVariable("roleId") Long roleId) {
        return new ResponseEntity<>(_userService.deleteRole(roleId, userId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserMessageDTO getUser(@PathVariable("id") UUID id){
        return _userService.getUser(id);
    }

}
