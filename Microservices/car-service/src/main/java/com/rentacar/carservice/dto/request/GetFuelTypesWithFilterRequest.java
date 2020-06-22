package com.rentacar.carservice.dto.request;

import lombok.Data;

@Data
public class GetFuelTypesWithFilterRequest {

    private String type;

    private String tankCapacity;
}
