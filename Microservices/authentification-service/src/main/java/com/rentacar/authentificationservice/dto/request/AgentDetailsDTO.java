package com.rentacar.authentificationservice.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class AgentDetailsDTO {

    private String username;

    private String password;

    private String email;

    private String name;

    private String PID;

    private Date dateFounded;

    private String bankAccountNumber;
}
