package com.rentacar.rentservice.entity;

import com.rentacar.rentservice.util.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request extends BaseEntity {

    // TODO(A) izmeniti ovo na Set<Car>, da ne bih morao splitovati
    private String carID;

    private UUID customerID;

    private UUID agentID;

    private RequestStatus status;

    private LocalDate receptionDate;       // datum prijema zahteva

    private LocalDate pickUpDate;          // datum preuzimanja

    private LocalTime pickUpTime;           // vreme preuzimanja

    private LocalDate returnDate;           // datum vracanja

    private LocalTime returnTime;           // vreme vracanja

    private Address pickUpAddress;

    private boolean deleted;

}
