package com.rentacar.searchservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultResponse {

    private AdSearchResponse ad;

    private AgentSearchResponse agent;

    private CarSearchResponse car;
}
