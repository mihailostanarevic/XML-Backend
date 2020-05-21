package com.rentacar.rentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class RentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentServiceApplication.class, args);
	}

}
