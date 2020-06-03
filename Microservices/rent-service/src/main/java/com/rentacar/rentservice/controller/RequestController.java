package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.dto.RequestDTO;
import com.rentacar.rentservice.service.IRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("/rent/request")
public class RequestController {
    
    private final IRequestService _requestService;

    public RequestController(IRequestService requestService) {
        _requestService = requestService;
    }

    @PostMapping()
    public ResponseEntity<?> createRequest(@RequestBody List<RequestDTO> requestList){
        _requestService.processRequests(requestList);
        return new ResponseEntity<>("Hello from Rent service", HttpStatus.OK);
    }

}
