package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.ApproveDenyAccessoryRequest;
import com.rentacar.carservice.service.IMessageCarAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message-car-accessories")
public class MessageCarAccessoriesController {

    @Autowired
    private IMessageCarAccessoryService _messageCarAccessoriesService;

    @PutMapping
    public void approveOrDeny(@RequestBody ApproveDenyAccessoryRequest request){
        System.out.println(request.getId());
        System.out.println(request.isApproved());
        _messageCarAccessoriesService.approveDenyAccessory(request);
    }
}
