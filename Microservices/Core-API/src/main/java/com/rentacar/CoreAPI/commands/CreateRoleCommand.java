package com.rentacar.CoreAPI.commands;

import com.rentacar.CoreAPI.dto.RoleList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleCommand {

    @TargetAggregateIdentifier
    private String ticketAggregateId;

    private UUID requestId;
    private UUID userId;
    private RoleList roleList;

}
