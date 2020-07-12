package com.rentacar.carservice.dto.soap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarClassSOAP {

    private UUID carClassID;

    private String name;

    private String description;

    private boolean deleted;
}
