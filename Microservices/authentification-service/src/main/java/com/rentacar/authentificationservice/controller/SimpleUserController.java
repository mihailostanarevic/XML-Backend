package com.rentacar.authentificationservice.controller;

import com.rentacar.authentificationservice.services.ISimpleUserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/simple-users")
public class SimpleUserController {

    private final ISimpleUserService _simpleUserService;

    public SimpleUserController(ISimpleUserService simpleUserService) {
        _simpleUserService = simpleUserService;
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
