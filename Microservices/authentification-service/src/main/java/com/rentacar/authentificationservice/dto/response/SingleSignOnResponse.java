package com.rentacar.authentificationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleSignOnResponse {

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
