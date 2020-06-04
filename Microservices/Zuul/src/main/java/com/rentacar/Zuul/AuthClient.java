package com.rentacar.Zuul;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth")
public interface AuthClient {

    @GetMapping("/auth/verify")
    boolean verify(String token);

}
