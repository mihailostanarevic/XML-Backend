package com.rentacar.authentificationservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateAdminRequestDTO {

    private UUID id;

    private String ime;

    private String prezime;
}
