package com.rentacar.rentservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestRequest {

    private UUID adID;

    private UUID agentID;

    private UUID customerID;

    private String customerUsername;

    private String pickUpDate;      // format -> "2016-06-12"

    private String pickUpTime;      // format -> "06:30"
    
    private String returnDate;

    private String returnTime;

    private String pickUpAddress;       // "Country, City, Street, Number"

    private boolean bundle;

}
