package com.rentacar.authentificationservice.dto.request;

import lombok.Data;

@Data
public class SimpleUserDetailsDTO {

    private String username;

    private String password;

    private String email;

    private String name;

    private String lastName;

    private String citizenNumber; //lazni JMBG

    private String address;

    private String city;

    private String country;
}
