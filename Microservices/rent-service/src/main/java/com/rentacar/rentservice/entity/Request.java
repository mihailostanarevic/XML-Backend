package com.rentacar.rentservice.entity;

import com.rentacar.rentservice.util.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;
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

    private Date receptionTime;       // datum prijema zahteva

    private Date pickUpDate;          // datum preuzimanja

    private Address pickUpAddress;

    private boolean deleted;

}
