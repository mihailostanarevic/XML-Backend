package com.rentacar.CoreAPI.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AdDetails {

    private UUID agentId;

//    private Car savedCar;

    private boolean limitedDistance;

    private String availableKilometersPerRent;

    private String kilometersTraveled;

    private int seats;

    private boolean cdw;

}
