package com.rentacar.carservice.client;

import com.rentacar.carservice.dto.feignClient.ReqDTO;
import com.rentacar.carservice.dto.feignClient.RequestAdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "rent")
public interface RentClient {

    @GetMapping("/request/{id}/simple-user")
    List<ReqDTO> getAllPaidRequestsByCustomer(@PathVariable UUID id);

    @GetMapping("/request-ads/{id}/request")
    List<RequestAdDTO> getAllRequestAdsByRequest(@PathVariable UUID id);
}
