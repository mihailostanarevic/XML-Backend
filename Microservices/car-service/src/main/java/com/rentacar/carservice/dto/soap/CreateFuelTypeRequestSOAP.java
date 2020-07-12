package com.rentacar.carservice.dto.soap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFuelTypeRequestSOAP {

    private UUID fuelTypeID;

    private String type;

    private String tankCapacity;

    private boolean gas;
}
