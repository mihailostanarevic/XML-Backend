package com.rentacar.authentificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
//@EnableEurekaClient
public class AuthentificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthentificationServiceApplication.class, args);
	}

}
