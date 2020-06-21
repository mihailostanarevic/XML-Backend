package com.rentacar.rentservice.dto.feignClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserDTO {

    private UUID id;

    private UserDTO user;

    private String firstName;

    private String lastName;

    private String ssn; //jmbg

    private String address;
}
