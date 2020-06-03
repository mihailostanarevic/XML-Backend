package com.rentacar.carservice.dto.request;

import lombok.Data;

@Data
public class CreateGearshiftTypeRequest {

    private String type;

    private String numberOfGears;
}
