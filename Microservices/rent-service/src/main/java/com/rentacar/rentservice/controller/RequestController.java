package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.service.implementation.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    RequestService _requestService;


}
