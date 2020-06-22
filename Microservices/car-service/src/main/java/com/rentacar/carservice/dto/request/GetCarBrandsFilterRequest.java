package com.rentacar.carservice.dto.request;

import lombok.Data;

@Data
public class GetCarBrandsFilterRequest {

    private String brandName;

    private String brandCountry;
}
