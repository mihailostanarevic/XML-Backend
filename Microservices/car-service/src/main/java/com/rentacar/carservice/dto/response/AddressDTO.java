package com.rentacar.carservice.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressDTO {

    private String city;

    private String street;

    private int number;


}
