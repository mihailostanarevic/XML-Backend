package com.rentacar.carservice.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@Data
public class AddAdRequest {

    private String carModel;

    private String gearshiftType;

    private String fuelType;

//    private List<String> photoUrls;

    private UUID agentId;

    private boolean limitedDistance;

    private String availableKilometersPerRent;

    private String kilometersTraveled;

    private int seats;

    private boolean cdw;

    private boolean simpleUser;
}
