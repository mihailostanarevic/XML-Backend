package com.rentacar.carservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class ApproveOrDenyCommentRequest {

    private UUID commentId;
}
