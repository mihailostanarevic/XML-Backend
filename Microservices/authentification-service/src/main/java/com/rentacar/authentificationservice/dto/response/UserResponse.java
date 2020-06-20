package com.rentacar.authentificationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;

    private String username;

    private String token;

    private String userRole;

    private int tokenExpiresIn;
}
