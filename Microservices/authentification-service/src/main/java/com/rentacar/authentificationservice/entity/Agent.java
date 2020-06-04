package com.rentacar.authentificationservice.entity;

import com.rentacar.authentificationservice.util.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agent extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String name;

    private String tin;

    private Date dateFounded;

    private String bankAccountNumber;

    private boolean blocked;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private String city;

}
