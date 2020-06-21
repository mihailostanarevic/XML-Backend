package com.rentacar.rentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestAd extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "ad_id")
    private UUID adID;

    private LocalDate pickUpDate;          // datum preuzimanja

    private LocalTime pickUpTime;           // vreme preuzimanja

    private LocalDate returnDate;           // datum vracanja

    private LocalTime returnTime;           // vreme vracanja

    private UUID report_id;
}