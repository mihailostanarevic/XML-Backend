package com.rentacar.carservice.dto.request;

import lombok.Data;

@Data
public class UpdateGearshiftTypeRequest {

    private String type;

    private String numberOfGears;
}
