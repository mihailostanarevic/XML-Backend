package com.rentacar.searchservice.clients;

import com.rentacar.searchservice.dto.response.RequestResponse;
import com.rentacar.searchservice.util.enums.RequestStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "rent")
public interface RentClient {

    @GetMapping("/request")
    List<RequestResponse> getRequestByStatus(@RequestParam("status") RequestStatus status);
}
