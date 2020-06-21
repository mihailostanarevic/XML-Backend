package com.rentacar.rentservice.dto.feignClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private UUID id;

    private String city;

    private String street;

    private int number;


}
