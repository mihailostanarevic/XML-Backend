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
public class RollbackAdCommand {

    @TargetAggregateIdentifier
    private UUID adId;
    private String status;

}
