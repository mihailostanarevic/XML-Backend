package com.rentacar.authentificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Agent extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String name;

    private String tin;                     //tax identification number (pib)

    private Date dateFounded;

    private String bankAccountNumber;

    private String address;

    private UUID simpleUserId;

    public Agent() {
        this.dateFounded = new Date();
    }
}
