package com.rentacar.rentservice.dto.request;

import lombok.Data;

@Data
public class CreateReportRequest {

    private String description;

    private String kilometersTraveled;
}
