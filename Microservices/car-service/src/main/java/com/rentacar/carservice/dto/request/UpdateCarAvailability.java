package com.rentacar.carservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateCarAvailability {

    private UUID carID;

    private String status;

    private String dateFrom;

    private String dateTo;

    private String timeFrom;

    private String timeTo;

}
