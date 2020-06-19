package com.rentacar.searchservice.services;

import com.rentacar.searchservice.dto.response.SearchResultResponse;

import java.util.List;

public interface ISearchService {

    List<SearchResultResponse> searchAds(String city, String from, String to);

}
