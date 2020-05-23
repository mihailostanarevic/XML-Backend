package com.rentacar.searchservice.services;

import com.rentacar.searchservice.dto.AdvancedSearchParametersDTO;
import com.rentacar.searchservice.dto.SearchParametersDTO;
import com.rentacar.searchservice.dto.SearchResultsDTO;
import org.springframework.stereotype.Service;

public interface ISearchService {

    SearchResultsDTO searchAds(SearchParametersDTO request);
    SearchResultsDTO advancedSearchAds(AdvancedSearchParametersDTO request);

}
