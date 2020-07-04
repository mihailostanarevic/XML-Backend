package com.rentacar.carservice.aggregates;

import com.rentacar.CoreAPI.commands.CreateAdCommand;
import com.rentacar.CoreAPI.commands.RollbackAdCommand;
import com.rentacar.CoreAPI.events.AdCreatedEvent;
import com.rentacar.CoreAPI.events.AdRollbackEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class AdAggregate {

    @AggregateIdentifier
    private UUID adId;

    @CommandHandler
    public AdAggregate(CreateAdCommand createAdCommand) {
        AggregateLifecycle.apply(new AdCreatedEvent(createAdCommand.getAdId(),
                createAdCommand.getAdSaga()));
    }

    @EventSourcingHandler
    public void on(AdCreatedEvent adCreatedEvent) {
        this.adId = adCreatedEvent.getAdId();
    }

    @CommandHandler
    public void on(RollbackAdCommand rollbackAdCommand) {
        System.out.println("Ad " + rollbackAdCommand.getAdId() + " is failed to save in search service.");
        AggregateLifecycle.apply(new AdRollbackEvent(rollbackAdCommand.getAdId()));
    }

}
