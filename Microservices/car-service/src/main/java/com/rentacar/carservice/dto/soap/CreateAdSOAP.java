package com.rentacar.carservice.dto.soap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdSOAP {

    private UUID adID;

    private String carModel;

    private String gearshiftType;

    private String fuelType;

    private UUID agentId;

    private boolean limitedDistance;

    private String availableKilometersPerRent;

    private String kilometersTraveled;

    private int seats;

    private boolean cdw;

    private boolean simpleUser;

    private boolean deleted;
}
