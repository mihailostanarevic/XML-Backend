package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.RateAdRequest;
import com.rentacar.carservice.dto.response.AvgRatingResponse;
import com.rentacar.carservice.dto.response.RatingResponse;
import com.rentacar.carservice.service.IRatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final IRatingService _ratingService;

    public RatingController(IRatingService ratingService) {
        _ratingService = ratingService;
    }

    @PostMapping
    RatingResponse rateAd(@RequestBody RateAdRequest request) throws Exception{
        return _ratingService.rateAd(request);
    }

    @GetMapping("/{id}/simple-user")
    List<RatingResponse> getAllRatingsByCustomer(@PathVariable UUID id) throws Exception{
        return _ratingService.getAllRatingsByCustomer(id);
    }

    @GetMapping("/{id}/ad")
    List<RatingResponse> getAllRatingsByAd(@PathVariable UUID id) throws Exception{
        return _ratingService.getAllRatingsByAd(id);
    }

    @GetMapping("/avg/{id}/ad")
    AvgRatingResponse getAvgRatingByAd(@PathVariable UUID id) throws Exception{
        return _ratingService.getAvgRatingByAd(id);
    }
}
