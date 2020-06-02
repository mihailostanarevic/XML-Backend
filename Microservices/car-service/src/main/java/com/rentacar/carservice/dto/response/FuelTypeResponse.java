package com.rentacar.carservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelTypeResponse {

    private UUID id;

    private String type;

    private String tankCapacity;

    private boolean gas;
}
