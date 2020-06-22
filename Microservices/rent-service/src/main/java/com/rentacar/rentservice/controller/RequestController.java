package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.dto.request.RequestBodyID;
import com.rentacar.rentservice.dto.feignClient.RequestDTO;
import com.rentacar.rentservice.dto.request.RequestRequest;
import com.rentacar.rentservice.dto.response.AdResponse;
import com.rentacar.rentservice.dto.response.AgentRequests;
import com.rentacar.rentservice.dto.response.RequestResponseDTO;
import com.rentacar.rentservice.dto.response.SimpleUserRequests;
import com.rentacar.rentservice.service.IRequestService;
import com.rentacar.rentservice.util.enums.RequestStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/request")
public class RequestController {
    
    private final IRequestService _requestService;

    public RequestController(IRequestService requestService) {
        _requestService = requestService;
    }

    @PostMapping()
    public ResponseEntity<RequestResponseDTO> createRequest(@RequestBody List<RequestRequest> requestList){
        _requestService.processRequests(requestList);
        RequestResponseDTO requestResponseDTO = new RequestResponseDTO();
        requestResponseDTO.setMessage("Request is successfully created!");
        return new ResponseEntity<>(requestResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/availability")
//    @PreAuthorize("hasAuthority('UPDATE_AD')")
    public ResponseEntity<?> changeCarAvailability(@RequestBody RequestRequest request) throws Exception{
        _requestService.changeAdAvailability(request);
        return new ResponseEntity<>("successfully changed", HttpStatus.OK);
    }

    @PutMapping("/{userId}/requests/{reqID}/pay")
//    @PreAuthorize("hasAuthority('CREATE_REQUEST')")
    public ResponseEntity<Collection<SimpleUserRequests>> userPay(@RequestBody RequestBodyID requestBodyID){
        return new ResponseEntity<>(_requestService.payRequest(requestBodyID.getId(), requestBodyID.getRequestID()), HttpStatus.OK);
    }

    @PutMapping("/{id}/requests/{resID}/drop")
//    @PreAuthorize("hasAuthority('CREATE_REQUEST')")
    public ResponseEntity<Collection<SimpleUserRequests>> userDrop(@RequestBody RequestBodyID requestBodyID){
        return new ResponseEntity<>(_requestService.dropRequest(requestBodyID.getId(), requestBodyID.getRequestID()), HttpStatus.OK);
    }

    @GetMapping("/{agentId}/requests/{requestID}/approve")
//    @PreAuthorize("hasAuthority('APPROVE_REQUEST')")
    public ResponseEntity<Collection<AgentRequests>> approveRequest(@PathVariable("agentId") UUID agentId, @PathVariable("requestID") UUID reqID){
        return new ResponseEntity<>(_requestService.approveRequest(agentId, reqID), HttpStatus.OK);
    }

    @GetMapping("/agent/{id}/requests/{status}")
//    @PreAuthorize("hasAuthority('READ_REQUEST')")
    public ResponseEntity<Collection<AgentRequests>> getAllAgentRequests(@PathVariable("id") UUID agentId, @PathVariable("status") String status){
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
        Collection<AgentRequests> agentRequests = _requestService.getAllAgentRequests(agentId, carRequestStatus);
        return new ResponseEntity<>(agentRequests, HttpStatus.OK);
    }

    @GetMapping("/user/{id}/requests/{status}")
//    @PreAuthorize("hasAuthority('READ_REQUEST')")
    public ResponseEntity<List<SimpleUserRequests>> usersRequestFromStatus(@PathVariable("id") UUID userId, @PathVariable("status") String status){
        List<SimpleUserRequests> simpleUserRequests;
        if(status.equalsIgnoreCase("PENDING")) {
            simpleUserRequests = _requestService.getAllUserRequests(userId, RequestStatus.PENDING);
        } else if(status.equalsIgnoreCase("RESERVED")) {
            simpleUserRequests = _requestService.getAllUserRequests(userId, RequestStatus.RESERVED);
        } else if(status.equalsIgnoreCase("PAID")) {
            simpleUserRequests = _requestService.getAllUserRequests(userId, RequestStatus.PAID);
        } else if(status.equalsIgnoreCase("CHECKED")) {
            simpleUserRequests = _requestService.getAllUserRequests(userId, RequestStatus.CHECKED);
        } else {
            simpleUserRequests = _requestService.getAllUserRequests(userId, RequestStatus.CANCELED);
        }
        return new ResponseEntity<>(simpleUserRequests, HttpStatus.OK);
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

    @GetMapping("/{id}/simple-user")
    public List<RequestDTO> getAllPaidRequestsByCustomer(@PathVariable UUID id){
        return _requestService.getAllPaidRequestsByCustomer(id);
    }
}
