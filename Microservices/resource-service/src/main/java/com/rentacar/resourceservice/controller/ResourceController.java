package com.rentacar.resourceservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    @GetMapping("/hello")
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<>("Greetings from resource controller", HttpStatus.OK);
    }

}