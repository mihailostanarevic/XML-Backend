package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.response.AvgRatingResponse;
import com.rentacar.carservice.dto.request.RateAdRequest;
import com.rentacar.carservice.dto.response.RatingResponse;

import java.util.List;
import java.util.UUID;

public interface IRatingService {

    RatingResponse rateAd(RateAdRequest request) throws Exception;

    List<RatingResponse> getAllRatingsByCustomer(UUID id) throws Exception;

    List<RatingResponse> getAllRatingsByAd(UUID id) throws Exception;

    AvgRatingResponse getAvgRatingByAd(UUID id) throws Exception;
}
