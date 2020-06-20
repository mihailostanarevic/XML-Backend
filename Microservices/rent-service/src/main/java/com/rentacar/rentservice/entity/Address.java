package com.rentacar.rentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {

    private String street;

    private int number;

    private String city;

    private String country;

    @OneToMany(mappedBy = "pickUpAddress", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Request> request;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AgentAddress> agent = new HashSet<>();

}
