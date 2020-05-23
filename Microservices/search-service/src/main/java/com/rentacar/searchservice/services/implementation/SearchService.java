package com.rentacar.searchservice.services.implementation;

import com.rentacar.searchservice.dto.AdvancedSearchParametersDTO;
import com.rentacar.searchservice.dto.SearchParametersDTO;
import com.rentacar.searchservice.dto.SearchResultsDTO;
import com.rentacar.searchservice.services.ISearchService;
import org.springframework.stereotype.Service;

@Service
public class SearchService implements ISearchService {
    @Override
    public SearchResultsDTO searchAds(SearchParametersDTO request) {
        return null;
    }

    @Override
    public SearchResultsDTO advancedSearchAds(AdvancedSearchParametersDTO request) {
        return null;
    }
}
