package com.rentacar.CoreAPI.events;

import com.rentacar.CoreAPI.dto.RoleList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreatedEvent {

    private UUID requestId;

    private UUID userId;
    private RoleList roleList;

}
