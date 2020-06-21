package com.rentacar.carservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveDenyAccessoryRequest {

    private UUID id;

    private boolean approved;
}
