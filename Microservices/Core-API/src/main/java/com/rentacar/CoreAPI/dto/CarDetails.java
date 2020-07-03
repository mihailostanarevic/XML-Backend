package com.rentacar.CoreAPI.dto;

import lombok.Data;

@Data
public class CarDetails {

    private String carModelName;

    private String gearshiftTypeName;

    private String fuelTypeName;

    private String kilometersTraveled;
}
