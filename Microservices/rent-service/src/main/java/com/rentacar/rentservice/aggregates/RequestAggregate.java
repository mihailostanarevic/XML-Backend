package com.rentacar.rentservice.aggregates;

import com.rentacar.CoreAPI.commands.CreateRequestCommand;
import com.rentacar.CoreAPI.events.RequestCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class RequestAggregate {

    @AggregateIdentifier
    private UUID requestId;

    public RequestAggregate() {
    }

    @CommandHandler
    public RequestAggregate(CreateRequestCommand createRequestCommand) {
        AggregateLifecycle.apply(new RequestCreatedEvent(createRequestCommand.getRequestId(),
                createRequestCommand.getUserId(),
                createRequestCommand.getRoleList()));
    }

    @EventSourcingHandler
    public void on(RequestCreatedEvent requestCreatedEvent) {
        this.requestId = requestCreatedEvent.getRequestId();
    }

}
