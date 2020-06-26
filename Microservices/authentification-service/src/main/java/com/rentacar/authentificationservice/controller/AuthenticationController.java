package com.rentacar.authentificationservice.controller;

import com.rentacar.authentificationservice.dto.request.*;
import com.rentacar.authentificationservice.dto.response.SingleSignOnResponse;
import com.rentacar.authentificationservice.dto.response.StringResponse;
import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.security.TokenUtils;
import com.rentacar.authentificationservice.services.IAuthenticationService;
import com.rentacar.authentificationservice.services.IUserService;
import com.rentacar.authentificationservice.util.enums.GeneralException;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final IAuthenticationService _authService;
    private final TokenUtils _tokenUtils;

    public AuthenticationController(IAuthenticationService authService, IUserService userService, TokenUtils tokenUtils) {
        this._authService = authService;
        _tokenUtils = tokenUtils;
    }

    @GetMapping("/verify")
    public String verify(@RequestHeader("Auth-Token") String token) throws NotFoundException {
        return _tokenUtils.getUsernameFromToken(token);
    }

    @GetMapping("/permission")
    public String getPermissions(@RequestHeader("Auth-Token") String token) throws NotFoundException {
        return _authService.getPermission(token);
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return new ResponseEntity<>("Hello from auth service", HttpStatus.OK);
    }

    @PutMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentialsDTO request, HttpServletRequest httpServletRequest) throws GeneralException {
        return new ResponseEntity<>(_authService.login(request, httpServletRequest), HttpStatus.OK);
    }

    @PostMapping("/create-agent")
    @PreAuthorize("hasAuthority('CREATE_AGENT')")
    public UserResponse createAgent(@RequestBody CreateAgentRequest request) {
//        validateAgentJSON(request);
        return _authService.createAgent(request);
    }

    @PostMapping("/create-simple-user")
    public UserResponse createSimpleUser(@RequestBody CreateSimpleUserRequest request) {
        return _authService.createSimpleUser(request);
    }

    @PutMapping("/{id}/new-password")
    public UserResponse newPassword(@PathVariable UUID id , @RequestBody NewPassordRequest request) {
        return _authService.setNewPassword(id,request);
    }

    @PutMapping("/confirm-registration-request")
    public void confirmRegistrationRequest(@RequestBody GetIdRequest request) {
        _authService.confirmRegistrationRequest(request);
    }

    @PutMapping("/approve-registration-request")
    public void approveRegistrationRequest(@RequestBody GetIdRequest request) {
        _authService.approveRegistrationRequest(request);
    }

    @PutMapping("/deny-registration-request")
    public void denyRegistrationRequest(@RequestBody GetIdRequest request) {
        _authService.denyRegistrationRequest(request);
    }

    @PostMapping("/ban")
    public void banUser(ChangePasswordDTO request){
        _authService.banUser(request);
    }

    @PostMapping("/password-change")
    public void passwordChange(ChangePasswordDTO request){
        _authService.changePassword(request);
    }

    @PutMapping("/admin")
    public void updateAdmin(UpdateAdminRequestDTO request) {
        _authService.updateAdmin(request);
    }

    @PutMapping("/agent")
    public void registerAgent(UpdateAgentRequestDTO request) {
        _authService.updateAgent(request);
    }

    @PutMapping("/user")
    public void updateSimpleUser(UpdateUserRequestDTO request) {
        _authService.updateSimpleUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(ChangePasswordDTO request){
        _authService.deleteUser(request);
    }

    @GetMapping("/logging-limit")
    public StringResponse loggingLimit(HttpServletRequest request){ return _authService.limitRedirect(request); }

    @PutMapping("/single-sign-on")
    public SingleSignOnResponse singleSignOn(@RequestBody LoginCredentialsDTO request) throws GeneralException{
        return _authService.singleSignOn(request);}
}
