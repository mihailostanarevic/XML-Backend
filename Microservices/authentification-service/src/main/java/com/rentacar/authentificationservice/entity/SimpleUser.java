package com.rentacar.authentificationservice.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SimpleUser extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String name;

    private String lastName;

    private String citizenNumber; //lazni JMBG

    private String address;

    private String city;

    private String country;
}
