package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.dto.feignClient.RequestAdDTO;
import com.rentacar.rentservice.service.IRequestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/request-ads")
public class RequestAdController {

    private final IRequestService _requestService;

    public RequestAdController(IRequestService requestService) {
        _requestService = requestService;
    }

    @GetMapping("/{id}/request")
    public List<RequestAdDTO> getAllRequestAdsByRequest(@PathVariable UUID id){
        return _requestService.getAllRequestAdsByRequest(id);
    }
}
