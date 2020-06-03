package com.rentacar.carservice.dto.request;

import lombok.Data;

@Data
public class UpdateFuelTypeRequest {

    private String type;

    private String tankCapacity;

    private boolean gas;
}
