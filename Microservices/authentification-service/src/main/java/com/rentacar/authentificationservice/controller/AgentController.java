package com.rentacar.authentificationservice.controller;

import com.rentacar.authentificationservice.dto.feignClient.AgentDTO;
import com.rentacar.authentificationservice.dto.feignClient.UserMessageDTO;
import com.rentacar.authentificationservice.dto.response.AgentResponse;
import com.rentacar.authentificationservice.services.IAgentService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/deactivate/{id}/agent") // deny/{id}/agent ?  //Mislim da ovde treba putanja da bude /{id}/deactivate
    public void deactivateAgent(@PathVariable UUID id) throws Exception{
        _agentService.deactivateAgentByAdmin(id);
    }

    @PutMapping("/delete/{id}/agent")
    public void deleteAgent(@PathVariable UUID id) throws Exception{
        _agentService.deleteAgentByAdmin(id);
    }

    @GetMapping("/{id}/address")
    public String getAgentAddress(@PathVariable UUID id) throws Exception{
        return _agentService.getAgentAddress(id);
    }

    @GetMapping("/{id}/get")
    public AgentResponse getOneAgent(@PathVariable UUID id) throws Exception{
        return _agentService.getOneAgent(id);
    }

    @GetMapping("/{id}")
    public AgentDTO getAgent(@PathVariable UUID id) throws Exception{
        return _agentService.getAgent(id);
    }

    @GetMapping("/{id}/user")
    public UserMessageDTO getUserFromAgent(@PathVariable("id") UUID id){
        return _agentService.getUserFromAgent(id);
    }

}
