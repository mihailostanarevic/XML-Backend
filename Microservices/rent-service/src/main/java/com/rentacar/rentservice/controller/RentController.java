package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.service.IPricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rent")
public class RentController {

    @GetMapping("/hello")
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<>(String.format("Hello from Rent service"), HttpStatus.OK);
    }

}
