package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.dto.feignClient.RequestDTO;
import com.rentacar.rentservice.dto.request.RequestRequest;
import com.rentacar.rentservice.service.IRequestService;
import com.rentacar.rentservice.util.enums.RequestStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("/request")
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

    @GetMapping
    List<RequestDTO> getRequestByStatus(@RequestParam("status") String status){
        String stringStatus = status.toUpperCase();
        RequestStatus statusReq;
        switch (stringStatus){
            case "PAID" : statusReq = RequestStatus.PAID;
                break;
            case "CANCELED" : statusReq = RequestStatus.CANCELED;
                break;
            case "CONFIRMED" : statusReq = RequestStatus.CONFIRMED;
                break;
            case "DENIED" : statusReq = RequestStatus.DENIED;
                break;
            case "APPROVED" : statusReq = RequestStatus.APPROVED;
                break;
            case "RESERVED" : statusReq = RequestStatus.RESERVED;
                break;
            default: statusReq = RequestStatus.PENDING;
        }
        return _requestService.getRequestsByStatus(statusReq);
    }


}
