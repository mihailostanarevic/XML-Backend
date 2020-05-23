package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.service.IRequestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("/request")
public class RequestController {
    
    private final IRequestService _requestService;

    public RequestController(IRequestService requestService) {
        _requestService = requestService;
    }
}
