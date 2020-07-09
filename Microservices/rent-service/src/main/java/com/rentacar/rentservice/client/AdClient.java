package com.rentacar.rentservice.client;

import com.rentacar.rentservice.dto.client.AdClientResponse;
import com.rentacar.rentservice.dto.client.AdCreationDateDTO;
import com.rentacar.rentservice.dto.client.AgentResponse;
import com.rentacar.rentservice.dto.response.CarResponse;
import com.rentacar.rentservice.dto.response.RatingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "ad")
public interface AdClient {

    @GetMapping(value = "/ads/{id}", consumes= MediaType.APPLICATION_JSON_VALUE)
    AdClientResponse getAdByID(@PathVariable("id") UUID id);

    @GetMapping(value = "/ads/{id}/agent", consumes= MediaType.APPLICATION_JSON_VALUE)
    AgentResponse getAgentIDByAdID(@PathVariable("id") UUID id);

    @GetMapping("/ratings/{id}/ad")
    List<RatingResponse> allRatingsByAd_Id(@PathVariable("id") UUID id);

    @GetMapping("/ads/{id}/car")
    CarResponse getCarFromAdId(@PathVariable("id") UUID adID);

    @GetMapping("/ads/{id}/creation-date")
    AdCreationDateDTO getDateOfCreation(@PathVariable("id") UUID id);
}
