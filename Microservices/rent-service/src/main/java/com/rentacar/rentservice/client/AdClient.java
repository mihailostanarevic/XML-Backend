package com.rentacar.rentservice.client;

import com.rentacar.rentservice.dto.client.AdClientResponse;
import com.rentacar.rentservice.dto.client.AgentResponse;
import com.rentacar.rentservice.dto.client.UUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "ad")
public interface AdClient {

    @GetMapping(value = "/ads/{id}", consumes= MediaType.APPLICATION_JSON_VALUE)
    AdClientResponse getAdByID(@PathVariable("id") UUID id);

    @GetMapping(value = "/{id}/agent", consumes= MediaType.APPLICATION_JSON_VALUE)
    AgentResponse getAgentIDByAdID(@PathVariable("id") UUID id);

}
