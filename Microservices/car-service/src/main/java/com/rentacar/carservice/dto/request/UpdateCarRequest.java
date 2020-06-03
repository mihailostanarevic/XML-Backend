package com.rentacar.carservice.dto.request;

import lombok.Data;

@Data
public class UpdateCarRequest {

    private String kilometersTraveled;

    private boolean gas; //fuelType
}
