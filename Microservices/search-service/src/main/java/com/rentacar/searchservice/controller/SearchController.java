package com.rentacar.searchservice.controller;

import com.rentacar.searchservice.dto.AdvancedSearchParametersDTO;
import com.rentacar.searchservice.dto.SearchParametersDTO;
import com.rentacar.searchservice.dto.SearchResultsDTO;
import com.rentacar.searchservice.services.ISearchService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/search")
public class SearchController {

    private final ISearchService searchService;

    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/")
    public SearchResultsDTO searchAds(SearchParametersDTO request) {
        return searchService.searchAds(request);
    }

    @PostMapping("/advanced")
    public SearchResultsDTO advancedSearchAds(AdvancedSearchParametersDTO request) {
        return searchService.advancedSearchAds(request);
    }


}
