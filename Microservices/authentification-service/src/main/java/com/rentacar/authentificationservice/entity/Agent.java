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
    private Set<Address> address;

    @ElementCollection
    @Column(name = "ad_id")
    @CollectionTable(name = "agent_ads", joinColumns = @JoinColumn(name = "agent_id"))
    private Set<UUID> adIDs = new HashSet<>();

    @ElementCollection
    @Column(name = "comment_id")
    @CollectionTable(name = "agent_comments", joinColumns = @JoinColumn(name = "agent_id"))
    private Set<UUID> commentsIDs = new HashSet<>();
}
