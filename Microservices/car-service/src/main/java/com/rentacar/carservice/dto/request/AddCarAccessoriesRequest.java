package com.rentacar.carservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class AddCarAccessoriesRequest {

    private UUID carAccessoriesId;

    private UUID carId;
}
