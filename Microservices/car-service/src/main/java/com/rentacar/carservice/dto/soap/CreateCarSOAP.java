package com.rentacar.carservice.dto.soap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarSOAP {

    private UUID carID;

    private String kilometersTraveled;

    private UUID carModelId;

    private UUID gearshiftTypeId;

    private UUID fuelTypeId;

    private boolean deleted;
}
