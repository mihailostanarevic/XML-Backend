package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.dto.request.RequestBodyID;
import com.rentacar.rentservice.dto.request.RequestRequest;
import com.rentacar.rentservice.dto.response.AdResponse;
import com.rentacar.rentservice.dto.response.AgentRequests;
import com.rentacar.rentservice.dto.response.SimpleUserRequests;
import com.rentacar.rentservice.service.IRequestService;
import com.rentacar.rentservice.util.enums.RequestStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

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

    @PutMapping("/{id}/requests/{resID}/pay")
//    @PreAuthorize("hasAuthority('CREATE_REQUEST')")
    public ResponseEntity<Collection<SimpleUserRequests>> userPay(@RequestBody RequestBodyID requestBodyID){
        return new ResponseEntity<>(_requestService.payRequest(requestBodyID.getId(), requestBodyID.getRequestID()), HttpStatus.OK);
    }

    @PutMapping("/{id}/requests/{resID}/drop")
//    @PreAuthorize("hasAuthority('CREATE_REQUEST')")
    public ResponseEntity<Collection<SimpleUserRequests>> userDrop(@RequestBody RequestBodyID requestBodyID){
        return new ResponseEntity<>(_requestService.dropRequest(requestBodyID.getId(), requestBodyID.getRequestID()), HttpStatus.OK);
    }

    @GetMapping("/{id}/requests/{status}")
//    @PreAuthorize("hasAuthority('READ_REQUEST')")
    public ResponseEntity<Collection<AgentRequests>> getAllAgentRequests(@PathVariable("id") UUID userId, @PathVariable("status") String status){
        RequestStatus carRequestStatus;
        if(status.equalsIgnoreCase("PENDING")) {
            carRequestStatus = RequestStatus.PENDING;
        } else if(status.equalsIgnoreCase("RESERVED")) {
            carRequestStatus = RequestStatus.RESERVED;
        } else if(status.equalsIgnoreCase("PAID")) {
            carRequestStatus = RequestStatus.PAID;
        } else if(status.equalsIgnoreCase("CHECKED")) {
            carRequestStatus = RequestStatus.CHECKED;
        } else {
            carRequestStatus = RequestStatus.CANCELED;
        }
        Collection<AgentRequests> agentRequests = _requestService.getAllAgentRequests(userId, carRequestStatus);
        return new ResponseEntity<>(agentRequests, HttpStatus.OK);
    }

    @GetMapping("/{id}/requests/{resID}/approve")
//    @PreAuthorize("hasAuthority('APPROVE_REQUEST')")
    public ResponseEntity<?> approveRequest(@PathVariable("id") UUID agentId, @PathVariable("resID") UUID reqID){
        return new ResponseEntity<>(_requestService.approveRequest(agentId, reqID), HttpStatus.OK);
    }

}
