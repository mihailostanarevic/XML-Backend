package com.rentacar.rentservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestBodyID {

    private UUID id;

    private UUID requestID;

}
