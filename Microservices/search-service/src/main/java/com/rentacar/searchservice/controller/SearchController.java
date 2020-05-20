package com.rentacar.searchservice.controller;

import com.rentacar.searchservice.dto.AdvancedSearchParametersDTO;
import com.rentacar.searchservice.dto.SearchParametersDTO;
import com.rentacar.searchservice.dto.SearchResultsDTO;
import com.rentacar.searchservice.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping("/")
    public SearchResultsDTO searchAds(SearchParametersDTO request) {
        return searchService.searchAds(request);
    }

    @PostMapping("/advanced")
    public SearchResultsDTO advancedSearchAds(AdvancedSearchParametersDTO request) {
        return searchService.advancedSearchAds(request);
    }


}
