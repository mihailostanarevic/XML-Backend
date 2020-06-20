package com.rentacar.rentservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "auth")
public interface AuthClient {



}

