package com.rentacar.searchservice.dto.request;

import lombok.Data;

@Data
public class AdvancedSearchParametersDTO {

    private String brandName;

    private String modelName;

    private String fuelType;

    private String gearshiftType;

    private String carClass;

    private int minPrice;

    private int maxPrice;

    private int distanceTraveled;

    private int estimatedTravelLength;

    private boolean collisionDamageWaiver;

    private int childSeats;
}
