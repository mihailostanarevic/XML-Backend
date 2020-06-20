package com.rentacar.authentificationservice.entity;

import com.rentacar.authentificationservice.util.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
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

    private String ssn;                 //jmbg

    private String address;

    private String city;

    private String country;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private LocalDateTime confirmationTime;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<UUID> requestIDs;

//    @OneToMany(mappedBy = "simpleUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<UUID> ratingsIDs;

//    @OneToMany(mappedBy = "simpleUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<UUID> commentsIDs;
}
