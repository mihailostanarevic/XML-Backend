package com.rentacar.authentificationservice.dto.request;

import lombok.Data;

@Data
public class LoginCredentialsDTO {

    private String username;

    private String password;
}
