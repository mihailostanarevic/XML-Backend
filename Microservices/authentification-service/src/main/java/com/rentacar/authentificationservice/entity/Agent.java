package com.rentacar.authentificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    private String tin;                     //tax identification number (pib)

    private Date dateFounded;

    private String bankAccountNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Address> address;            // format: "Country, City, Street, Number"

//    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ElementCollection
    @CollectionTable(name = "agent_ads", joinColumns = @JoinColumn(name = "agent_id"))
    @Column(name = "ad_id")
    private Set<UUID> adIDs;

//    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //
//    private List<UUID> commentsIDs;
}
