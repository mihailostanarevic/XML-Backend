package com.rentacar.resourceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class ResourceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServiceApplication.class, args);
	}

}
