package com.rentacar.carservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class AddCommentRequest {

    private UUID userId;

    private String comment;

    private UUID adId;
}
