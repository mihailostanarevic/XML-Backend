package com.rentacar.authentificationservice.entity;

import com.rentacar.authentificationservice.util.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUser extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String firstName;

    private String lastName;

    private String ssn; //lazni JMBG

    private String address;

    private String city;

    private String country;

//    private boolean blocked;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus; //approving when user register
}
