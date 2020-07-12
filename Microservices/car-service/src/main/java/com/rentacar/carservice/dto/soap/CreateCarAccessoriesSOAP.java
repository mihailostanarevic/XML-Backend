package com.rentacar.carservice.dto.soap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarAccessoriesSOAP {

    private UUID carAccessoriesID;

    private String description;

    private boolean deleted;
}
