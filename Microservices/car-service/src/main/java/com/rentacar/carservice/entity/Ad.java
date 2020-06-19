package com.rentacar.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Ad extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    private UUID agent;

    private boolean available; //is rented or available

    private boolean limitedDistance; //is distance which user can travel limited

    private String availableKilometersPerRent; //if distance is limited

    private int seats; //child seats

    private boolean cdw;

    @OneToMany(mappedBy = "ad")
    private Set<Photo> adPhotos;

    private LocalDate creationDate; //date when ad was created

    private boolean deleted;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //
    private List<Rating> ratings;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //
    private List<Comment> comments;

    public Ad() {
        this.available = true;
        this.deleted = false;
        this.creationDate = LocalDate.now();
    }
}
