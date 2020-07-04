package com.rentacar.CoreAPI.commands;

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
public class RollbackRequestCommand {

    @TargetAggregateIdentifier
    private UUID requestId;
    private String status;

}
