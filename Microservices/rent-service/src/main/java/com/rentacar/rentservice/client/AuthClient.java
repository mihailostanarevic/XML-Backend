package com.rentacar.rentservice.client;

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

    @GetMapping(value = "/simple-users/{username}", consumes= MediaType.APPLICATION_JSON_VALUE)
    UUIDResponse getIDByUsername(@PathVariable("username") String username);

    @PutMapping("/simple-users/{id}/{userRole}")
    String  addUserRole(@PathVariable("id") UUID simpleUserID,
                        @PathVariable("userRole") String userRole);

    @GetMapping("/simple-users/get/{id}")
    SimpleUserDTO getSimpleUser(@PathVariable("id") UUID id);
}

