package com.rentacar.authentificationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SimpleUserAgentIdResponse {

    private UUID agentId;

}
