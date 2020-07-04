package com.rentacar.rentservice.saga;

import com.rentacar.CoreAPI.commands.CreateRoleCommand;
import com.rentacar.CoreAPI.commands.RollbackRequestCommand;
import com.rentacar.CoreAPI.events.RequestCreatedEvent;
import com.rentacar.CoreAPI.events.RoleCreatedEvent;
import com.rentacar.CoreAPI.events.RoleCreatedFailedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class RequestManagementSaga {

    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "requestId")
    public void handle(RequestCreatedEvent requestCreatedEvent) {           
        System.out.println("Saga invoked");

        String ticketAggregateId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("ticketAggregateId", ticketAggregateId);

        commandGateway.send(new CreateRoleCommand(ticketAggregateId, requestCreatedEvent.getRequestId(),
                requestCreatedEvent.getUserId(), requestCreatedEvent.getRoleList()));
    }

    @SagaEventHandler(associationProperty = "ticketAggregateId")
    public void handle(RoleCreatedEvent roleCreatedEvent) {
        System.out.println("Saga finishing, both request and role created!");

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "ticketAggregateId")
    public void handle(RoleCreatedFailedEvent ticketCreatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        SagaLifecycle.end();
        commandGateway.send(new RollbackRequestCommand(ticketCreatedFailedEvent.getRequestId(), ticketCreatedFailedEvent.getReason()));
    }

}
