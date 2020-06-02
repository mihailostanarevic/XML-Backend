package com.rentacar.carservice.dto.request;

import lombok.Data;

@Data
public class CreateCarClassRequest {

    private String name;

    private String description;
}
