package com.rentacar.carservice.dto.request;

import lombok.Data;

@Data
public class GetGearshiftTypesWithFilterRequest {

    private String type;

    private String numberOfGears;
}
