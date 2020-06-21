package com.rentacar.authentificationservice.controller;

import com.rentacar.authentificationservice.dto.client.CustomerResponse;
import com.rentacar.authentificationservice.dto.client.UUIDResponse;
import com.rentacar.authentificationservice.services.ISimpleUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/simple-users")
public class SimpleUserController {

    private final ISimpleUserService _simpleUserService;

    public SimpleUserController(ISimpleUserService simpleUserService) {
        _simpleUserService = simpleUserService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UUIDResponse> findIDByUsername(@PathVariable String username) throws Exception{
        return new ResponseEntity<>(_simpleUserService.getIDByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerByID(@PathVariable("id") UUID id) throws Exception{
        return new ResponseEntity<>(_simpleUserService.getCustomerByID(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/{userRole}")
    public ResponseEntity<?> addUserRole(@PathVariable("id") UUID simpleUserID, @PathVariable("userRole") String userRole) throws Exception{
        _simpleUserService.addUserRole(simpleUserID, userRole);
        return new ResponseEntity<>("Successfully changed", HttpStatus.OK);
    }

    @PutMapping("/block/{id}/simple-user")
    public void blockAgent(@PathVariable UUID id) throws Exception{
        _simpleUserService.blockSimpleUserByAdmin(id);
    }

    @PutMapping("/unblock/{id}/simple-user")
    public void unblockAgent(@PathVariable UUID id) throws Exception{
        _simpleUserService.unblockSimpleUserByAdmin(id);
    }

    @PutMapping("/activate/{id}/simple-user") // approve/{id}/simple-user ?
    public void activateAgent(@PathVariable UUID id) throws Exception{
        _simpleUserService.activateSimpleUserByAdmin(id);
    }

    @PutMapping("/deactivate/{id}/simple-user") // deny/{id}/simple-user ?
    public void deactivateAgent(@PathVariable UUID id) throws Exception{
        _simpleUserService.deactivateSimpleUserByAdmin(id);
    }

    @PutMapping("/delete/{id}/simple-user")
    public void deleteAgent(@PathVariable UUID id) throws Exception{
        _simpleUserService.deleteSimpleUserByAdmin(id);
    }

}
