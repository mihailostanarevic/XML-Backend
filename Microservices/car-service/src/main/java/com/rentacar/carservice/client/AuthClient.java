package com.rentacar.carservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "auth")
public interface AuthClient {

    @GetMapping("/simple-users/{username}")
    UUID getIDByUsername(@PathVariable("username") String username);

    @GetMapping("/agents/{id}/address")
    String getAgentAddress(@PathVariable("id") UUID id);

}
