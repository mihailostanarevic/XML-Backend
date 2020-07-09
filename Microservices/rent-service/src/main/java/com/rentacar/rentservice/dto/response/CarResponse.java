package com.rentacar.rentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private UUID carID;

    private String carModelName;

    private String carBrandName;

    private String carClassName;

    private String carClassDescription;

    private String carFuelType;

    private String carTankCapacity;

    private boolean isGas;

    private String carGearshiftType;

    private String carNumberOfGears;

    private String kilometersTraveled;

}
