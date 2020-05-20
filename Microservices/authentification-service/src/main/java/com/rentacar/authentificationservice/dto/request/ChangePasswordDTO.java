package com.rentacar.authentificationservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class ChangePasswordDTO {

    private UUID id;

    private String role;
}
