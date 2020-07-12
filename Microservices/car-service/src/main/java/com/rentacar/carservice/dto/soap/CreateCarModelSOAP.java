package com.rentacar.carservice.dto.soap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarModelSOAP {

    private UUID carModelID;

    private String name;

    private UUID carBrand;

    private UUID carClass;

    private boolean deleted;
}
