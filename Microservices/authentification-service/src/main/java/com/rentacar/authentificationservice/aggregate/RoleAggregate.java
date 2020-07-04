package com.rentacar.authentificationservice.aggregate;

import com.rentacar.CoreAPI.commands.CreateRoleCommand;
import com.rentacar.CoreAPI.events.RoleCreatedEvent;
import com.rentacar.CoreAPI.events.RoleCreatedFailedEvent;
import com.rentacar.authentificationservice.services.implementation.AuthenticationService;
import javassist.NotFoundException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class RoleAggregate {

    @AggregateIdentifier
    private String ticketAggregateId;

    public RoleAggregate() {
    }

    @CommandHandler
    public RoleAggregate(CreateRoleCommand createRoleCommand, AuthenticationService authService) {
        try {
            authService.addUserRole(createRoleCommand.getUserId(), createRoleCommand.getRoleList());

            AggregateLifecycle.apply(new RoleCreatedEvent(createRoleCommand.getTicketAggregateId()));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());

            AggregateLifecycle.apply(new RoleCreatedFailedEvent(createRoleCommand.getTicketAggregateId(), createRoleCommand.getRequestId(), e.getMessage()));
        }
    }

    @EventSourcingHandler
    protected void on(RoleCreatedEvent ticketCreatedEvent) {
        this.ticketAggregateId = ticketCreatedEvent.getTicketAggregateId();
    }

    @EventSourcingHandler
    protected void on(RoleCreatedFailedEvent ticketCreatedFailedEvent) {
        this.ticketAggregateId = ticketCreatedFailedEvent.getTicketAggregateId();
    }
}
