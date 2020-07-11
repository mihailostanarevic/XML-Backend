package com.rentacar.searchservice.controller;

import com.rentacar.searchservice.dto.request.AdvancedSearchParametersDTO;
import com.rentacar.searchservice.dto.response.SearchResultResponse;
import com.rentacar.searchservice.services.ISearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/search")
public class SearchController {

    private final ISearchService searchService;

    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/light")
    public List<SearchResultResponse> search(@RequestParam(value="city") String city,
                                             @RequestParam(value="from") String from,
                                             @RequestParam(value="to") String to){
        return searchService.searchAds(city, from, to);
    }

    @GetMapping("/advanced")
    public List<SearchResultResponse> advancedSearch(@RequestParam("city") String city,@RequestParam("from") String from,@RequestParam("to") String to,@RequestParam("brand") String brand, @RequestParam("model") String model, @RequestParam("fuelType") String fuelType
            , @RequestParam("gearshiftType") String gearshiftType, @RequestParam("carClass") String carClass, @RequestParam("priceFrom") int priceFrom, @RequestParam("priceTo") int priceTo
            , @RequestParam("estimatedDistance") int estimatedDistance, @RequestParam("cdw") boolean cdw, @RequestParam("childrenSeats") int childrenSeats){
        return searchService.advancedSearch(city, from, to, brand, model, fuelType, gearshiftType, carClass, priceFrom, priceTo, estimatedDistance, cdw, childrenSeats);
    }

}
