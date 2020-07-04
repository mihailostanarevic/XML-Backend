package com.rentacar.searchservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentSearchResponse {

    private UUID agentID;

    private UUID simpleUserID;

    private String agentName;

    private String dateFounded;

    private String locations;
}
