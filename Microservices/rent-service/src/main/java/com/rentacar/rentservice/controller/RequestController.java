package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.dto.request.RequestRequest;
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
    public ResponseEntity<?> createRequest(@RequestBody List<RequestRequest> requestList){
        _requestService.processRequests(requestList);
        return new ResponseEntity<>("Hello from Rent service", HttpStatus.OK);
    }

    @PostMapping("/availability")
//    @PreAuthorize("hasAuthority('UPDATE_AD')")
    public ResponseEntity<?> changeCarAvailability(@RequestBody RequestRequest request) throws Exception{
        _requestService.changeAdAvailability(request);
        return new ResponseEntity<>("successfully changed", HttpStatus.OK);
    }

}
