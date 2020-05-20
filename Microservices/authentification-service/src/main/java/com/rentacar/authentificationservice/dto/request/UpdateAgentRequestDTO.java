package com.rentacar.authentificationservice.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UpdateAgentRequestDTO {

    private UUID id;

    private String name;

    private String PID;

    private Date dateFounded;

    private String bankAccountNumber;
}
