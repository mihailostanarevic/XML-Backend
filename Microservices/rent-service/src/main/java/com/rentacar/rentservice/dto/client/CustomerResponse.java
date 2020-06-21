package com.rentacar.rentservice.dto.client;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String username;

    private String userRole;

    private String ssn;                 //jmbg

    private String address;

}
