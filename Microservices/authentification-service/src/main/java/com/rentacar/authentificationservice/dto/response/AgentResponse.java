package com.rentacar.authentificationservice.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class AgentResponse {

    private UUID id;

    private String username;

    private boolean deleted;

    private String name;

    private String tin;

    private String bankAccountNumber;

    private String address;

}
