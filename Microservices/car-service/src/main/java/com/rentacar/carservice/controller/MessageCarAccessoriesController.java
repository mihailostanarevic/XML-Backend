package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.ApproveDenyAccessoryRequest;
import com.rentacar.carservice.service.IMessageCarAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/message-car-accessories")
public class MessageCarAccessoriesController {

    @Autowired
    private IMessageCarAccessoryService _messageCarAccessoriesService;

    @PutMapping
    @PreAuthorize("hasAuthority('RECEIVE_MESSAGE')")
    public void approveOrDeny(@RequestBody ApproveDenyAccessoryRequest request){
        System.out.println(request.getId());
        System.out.println(request.isApproved());
        _messageCarAccessoriesService.approveDenyAccessory(request);
    }
}
