package com.rentacar.searchservice.controller;

import com.rentacar.searchservice.dto.request.AdvancedSearchParametersDTO;
import com.rentacar.searchservice.dto.response.SearchResultResponse;
import com.rentacar.searchservice.services.ISearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/search")
public class SearchController {

    private final ISearchService searchService;

    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/light")
    public List<SearchResultResponse> search(@RequestParam(value="city") String city, @RequestParam(value="from") String from, @RequestParam(value="to") String to){
        return searchService.searchAds(city, from, to);
    }
}
