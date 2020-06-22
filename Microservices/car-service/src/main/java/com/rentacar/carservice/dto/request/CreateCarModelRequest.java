package com.rentacar.carservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCarModelRequest {

    private String name;

    private UUID brandId;

    private UUID classId;
}
