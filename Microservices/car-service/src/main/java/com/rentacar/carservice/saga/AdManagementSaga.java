package com.rentacar.carservice.saga;

import com.rentacar.CoreAPI.commands.CreateAdSearchCommand;
import com.rentacar.CoreAPI.events.AdCreatedEvent;
import com.rentacar.CoreAPI.events.AdRollbackEvent;
import com.rentacar.CoreAPI.events.AdSearchCreatedEvent;
import com.rentacar.CoreAPI.events.AdSearchFailedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class AdManagementSaga {

    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "adId")
    public void handle(AdCreatedEvent adCreatedEvent) {
        System.out.println("Saga AD invoked");

        String adAggregateId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("adAggregateId", adAggregateId);

        commandGateway.send(new CreateAdSearchCommand(adAggregateId, adCreatedEvent.getAdId(), adCreatedEvent.getAdSaga()));
    }

    @SagaEventHandler(associationProperty = "adAggregateId")
    public void handle(AdSearchCreatedEvent adSearchCreatedEvent) {
        System.out.println("Saga AD finishing, both ad and adSearch created!");
        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "adAggregateId")
    public void handle(AdSearchFailedEvent adSearchFailedEvent) {
        System.out.println("Saga AD declined, starting compensation transaction!");
//        commandGateway.send(new RollbackOrderCommand(ticketCreatedFailedEvent.getOrderId(), ticketCreatedFailedEvent.getReason()));
    }

    @SagaEventHandler(associationProperty = "adId")
    public void handle(AdRollbackEvent adRollbackEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }


}
