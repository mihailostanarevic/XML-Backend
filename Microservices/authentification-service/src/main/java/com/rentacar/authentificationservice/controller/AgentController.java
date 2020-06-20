package com.rentacar.authentificationservice.controller;

import com.rentacar.authentificationservice.dto.response.AgentRequests;
import com.rentacar.authentificationservice.services.IAgentService;
import com.rentacar.authentificationservice.util.enums.RequestStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/agents")
public class AgentController {

    private final IAgentService _agentService;

    public AgentController(IAgentService agentService) {
        _agentService = agentService;
    }

    @PutMapping("/block/{id}/agent")
    public void blockAgent(@PathVariable UUID id) throws Exception{
        _agentService.blockAgentByAdmin(id);
    }

    @PutMapping("/unblock/{id}/agent")
    public void unblockAgent(@PathVariable UUID id) throws Exception{
        _agentService.unblockAgentByAdmin(id);
    }

    @PutMapping("/activate/{id}/agent") // approve/{id}/agent ?
    public void activateAgent(@PathVariable UUID id) throws Exception{
        _agentService.activateAgentByAdmin(id);
    }

    @PutMapping("/deactivate/{id}/agent") // deny/{id}/agent ?
    public void deactivateAgent(@PathVariable UUID id) throws Exception{
        _agentService.deactivateAgentByAdmin(id);
    }

    @PutMapping("/delete/{id}/agent")
    public void deleteAgent(@PathVariable UUID id) throws Exception{
        _agentService.deleteAgentByAdmin(id);
    }

}
