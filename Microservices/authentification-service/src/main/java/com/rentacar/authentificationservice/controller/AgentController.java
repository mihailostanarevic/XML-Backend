package com.rentacar.authentificationservice.controller;

import com.rentacar.authentificationservice.services.IAgentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
