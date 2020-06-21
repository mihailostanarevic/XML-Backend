package com.rentacar.searchservice.dto.feignClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {

    private UUID agentID;

    private String agentName;

    private String dateFounded;

    private String address;
}
