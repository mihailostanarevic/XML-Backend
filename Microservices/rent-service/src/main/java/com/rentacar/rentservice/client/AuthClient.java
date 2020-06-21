package com.rentacar.rentservice.client;

import com.rentacar.rentservice.dto.client.AgentResponse;
import com.rentacar.rentservice.dto.client.CustomerResponse;
import com.rentacar.rentservice.dto.feignClient.SimpleUserDTO;
import com.rentacar.rentservice.dto.client.UUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "auth")
public interface AuthClient {

    @GetMapping(value = "/simple-users/{username}/user", consumes= MediaType.APPLICATION_JSON_VALUE)
    UUIDResponse getIDByUsername(@PathVariable("username") String username);

    @GetMapping(value = "/simple-users/{id}", consumes= MediaType.APPLICATION_JSON_VALUE)
    CustomerResponse getSimpleUserByID(@PathVariable("id") UUID id);

    @PutMapping("/simple-users/{id}/{userRole}")
    String  addUserRole(@PathVariable("id") UUID simpleUserID,
                        @PathVariable("userRole") String userRole);

    @GetMapping(value = "/agents/{id}", consumes= MediaType.APPLICATION_JSON_VALUE)
    AgentResponse getAgentByAdID(@PathVariable("id") UUID id);

    @GetMapping("/simple-users/get/{id}")
    SimpleUserDTO getSimpleUser(@PathVariable("id") UUID id);
}

