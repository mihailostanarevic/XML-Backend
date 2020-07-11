package com.rentacar.searchservice.services;

import com.rentacar.CoreAPI.dto.AdSaga;
import com.rentacar.searchservice.dto.response.SearchResultResponse;

import java.util.List;
import java.util.UUID;

public interface ISearchService {

    List<SearchResultResponse> searchAds(String city, String from, String to);

    void createAd(UUID adId, AdSaga adSaga) throws Exception;

    List<SearchResultResponse> advancedSearch(String city, String from, String to, String brand, String model, String fuelType, String gearshiftType, String carClass, int priceFrom, int priceTo, int estimatedDistance, boolean cdw, int childrenSeats);
}
