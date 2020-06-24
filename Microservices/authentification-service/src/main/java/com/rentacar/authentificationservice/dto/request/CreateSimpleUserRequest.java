package com.rentacar.authentificationservice.dto.request;

import lombok.Data;

@Data
public class CreateSimpleUserRequest {

    private String username;

    private String password;

    private String rePassword;

    private String firstName;

    private String lastName;

    private String ssn;

    private String address;

    private String city;

    private String country;
}
