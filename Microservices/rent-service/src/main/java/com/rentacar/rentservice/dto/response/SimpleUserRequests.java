package com.rentacar.rentservice.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class SimpleUserRequests {

    private UUID id;

    private String agent;

    private String ad;

    private String receptionDate;

    private String pickUpAddress;

    private String requestStatus;

    private String description;

}
