package com.rentacar.searchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Ad {

    @Id
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    //kod requesta UUID od ad-a

    private UUID agent;

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
        this.deleted = false;
        this.creationDate = LocalDate.now();
    }
}