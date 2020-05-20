package com.rentacar.authentificationservice.dto.request;

import lombok.Data;

@Data
public class AdminDetailsDTO {

    private String username;

    private String password;

    private String email;

    private String ime;

    private String prezime;
}
