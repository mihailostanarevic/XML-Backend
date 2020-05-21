package com.rentacar.rentservice.entity;

import com.rentacar.rentservice.config.DateTimeConfig;
import com.rentacar.rentservice.util.enums.RequestStatus;
import lombok.*;

import javax.persistence.Entity;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request extends BaseEntity {

    private UUID carID;

    private UUID customerID;

    private UUID agentID;

    private RequestStatus status;

    private DateTimeConfig receptionTime;       // datum prijema zahteva

    private DateTimeConfig pickUpDate;          // datum preuzimanja

    private Address pickUpAddress;

    private boolean deleted;

}
