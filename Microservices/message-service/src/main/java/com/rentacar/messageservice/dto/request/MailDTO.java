package com.rentacar.messageservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class MailDTO {

    private UUID id;

    private String role;

    private String username;

    private String firstName;

    private String lastName;

    private String name;
}
