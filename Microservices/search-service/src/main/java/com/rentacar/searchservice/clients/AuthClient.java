package com.rentacar.searchservice.clients;

import com.rentacar.searchservice.dto.feignClient.AgentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "auth")
public interface AuthClient {

    @GetMapping("/agents/{id}/address")
    String getAgentAddress(@PathVariable UUID id);

    @GetMapping("/agents/{id}")
    AgentDTO getAgent(@PathVariable("id") UUID id);
}
