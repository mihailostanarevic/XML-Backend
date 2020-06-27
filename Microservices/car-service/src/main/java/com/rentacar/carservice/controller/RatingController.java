package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.RateAdRequest;
import com.rentacar.carservice.dto.response.AvgRatingResponse;
import com.rentacar.carservice.dto.response.RatingResponse;
import com.rentacar.carservice.service.IRatingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final IRatingService _ratingService;

    public RatingController(IRatingService ratingService) {
        _ratingService = ratingService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('POST_RATE')")
    RatingResponse rateAd(@RequestBody RateAdRequest request) throws Exception{
        return _ratingService.rateAd(request);
    }

    @GetMapping("/{id}/simple-user")
    @PreAuthorize("hasAuthority('VIEW_AD')")
    List<RatingResponse> getAllRatingsByCustomer(@PathVariable UUID id) throws Exception{
        return _ratingService.getAllRatingsByCustomer(id);
    }

    @GetMapping("/{id}/ad")
    @PreAuthorize("hasAuthority('VIEW_AD')")
    List<RatingResponse> getAllRatingsByAd(@PathVariable UUID id) throws Exception{
        return _ratingService.getAllRatingsByAd(id);
    }

    @GetMapping("/avg/{id}/ad")
    @PreAuthorize("hasAuthority('VIEW_AD')")
    AvgRatingResponse getAvgRatingByAd(@PathVariable UUID id) throws Exception{
        return _ratingService.getAvgRatingByAd(id);
    }
}
