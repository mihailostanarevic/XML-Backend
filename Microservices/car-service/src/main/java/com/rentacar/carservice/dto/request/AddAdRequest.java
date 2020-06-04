package com.rentacar.carservice.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@Data
@Getter
@Setter
public class AddAdRequest {

    private String carModel;

    private String gearshiftType;

    private String fuelType;

    private double priceFrom;

    private double priceTo;

    private UUID agentID;

    private double kilometersTraveled;         // predjena kilometraza

    private List<String> photosUrls;

    private String limitedDistance;

    private String availableKilometersPerRent;

    private int seats;

    private boolean cdw;
}
