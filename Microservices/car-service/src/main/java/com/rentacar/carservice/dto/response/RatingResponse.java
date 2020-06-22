package com.rentacar.carservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {

    private String grade; //rating

    private String customerFirstName;

    private String customerLastName;

    private String customerEmail;

    private String agentName;

    private String agentEmail;

    private String carBrandName;

    private String carModelName;

    private UUID ratingId;
}
