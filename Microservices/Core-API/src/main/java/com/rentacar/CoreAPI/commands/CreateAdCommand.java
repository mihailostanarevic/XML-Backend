package com.rentacar.CoreAPI.commands;

import com.rentacar.CoreAPI.dto.AdSaga;
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
public class CreateAdCommand {

    @TargetAggregateIdentifier
    private UUID adId;

    private AdSaga adSaga;

}
