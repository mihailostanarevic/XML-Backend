package com.rentacar.rentservice.client;

import com.rentacar.rentservice.dto.feignClient.SimpleUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "auth")
public interface AuthClient {

    @GetMapping("/simple-users/{username}")
    UUID getIDByUsername(@PathVariable("username") String username);

    @GetMapping("/simple-users/get/{id}")
    SimpleUserDTO getSimpleUser(@PathVariable("id") UUID id);
}

