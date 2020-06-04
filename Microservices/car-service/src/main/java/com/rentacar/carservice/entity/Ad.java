package com.rentacar.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Ad extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    private UUID agentID;           // agent who added this ad

    private boolean available;      //is rented or available

    private String limitedDistance;     //is distance which user can travel limited

    private String availableKilometersPerRent; //if distance is limited

    private String priceFromTo;     // cena od-do

    private int seats;               //child seats

    private boolean cdw;            // collision damage waver

    private LocalDate date;         //date when ad was created

    @OneToMany(mappedBy = "ad_id", cascade = CascadeType.ALL)
    private List<Date> reservedTerms;     // zauzeti termini

    private boolean deleted;

    public Ad() {
        this.available = true;
        this.deleted = false;
        this.date = LocalDate.now();
        this.comments = new ArrayList<>();
        this.photos = new ArrayList<>();
    }
}
