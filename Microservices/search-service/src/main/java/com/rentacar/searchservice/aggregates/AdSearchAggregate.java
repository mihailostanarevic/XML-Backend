package com.rentacar.searchservice.aggregates;

import com.rentacar.CoreAPI.commands.CreateAdSearchCommand;
import com.rentacar.CoreAPI.events.AdSearchCreatedEvent;
import com.rentacar.CoreAPI.events.AdSearchFailedEvent;
import com.rentacar.searchservice.services.implementation.SearchService;
import javassist.NotFoundException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AdSearchAggregate {

    @AggregateIdentifier
    private String adAggregateId;

    public AdSearchAggregate() {}

    @CommandHandler
    public AdSearchAggregate(CreateAdSearchCommand createAdSearchCommand, SearchService searchService) {
        try {
            searchService.createAd(createAdSearchCommand.getAdId(), createAdSearchCommand.getAdSaga());

            AggregateLifecycle.apply(new AdSearchCreatedEvent(createAdSearchCommand.getAdAggregateId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());

            AggregateLifecycle.apply(new AdSearchFailedEvent(createAdSearchCommand.getAdAggregateId(), createAdSearchCommand.getAdId(), e.getMessage()));
        }
    }

    @EventSourcingHandler
    protected void on(AdSearchCreatedEvent adSearchCreatedEvent) {
        this.adAggregateId = adSearchCreatedEvent.getAdAggregateId();
    }

    @EventSourcingHandler
    protected void on(AdSearchFailedEvent adSearchFailedEvent) {
        this.adAggregateId = adSearchFailedEvent.getAdAggregateId();
    }

}
