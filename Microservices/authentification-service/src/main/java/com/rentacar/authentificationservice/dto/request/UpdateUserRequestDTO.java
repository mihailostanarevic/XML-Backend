package com.rentacar.authentificationservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserRequestDTO {

    private UUID id;

    private String name;

    private String lastName;

    private String citizenNumber; //lazni JMBG

    private String address;

    private String city;

    private String country;
}
