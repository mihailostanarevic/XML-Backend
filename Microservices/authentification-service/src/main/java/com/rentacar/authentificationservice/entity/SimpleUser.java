package com.rentacar.authentificationservice.entity;

import com.rentacar.authentificationservice.util.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @ElementCollection
    @Column(name = "request_id")
    @CollectionTable(name = "user_requests", joinColumns = @JoinColumn(name = "user_id"))
    private Set<UUID> requestIDs = new HashSet<>();

    @ElementCollection
    @Column(name = "rating_id")
    @CollectionTable(name = "user_ratings", joinColumns = @JoinColumn(name = "user_id"))
    private Set<UUID> ratingsIDs = new HashSet<>();

    @ElementCollection
    @Column(name = "comment_id")
    @CollectionTable(name = "user_comments", joinColumns = @JoinColumn(name = "user_id"))
    private Set<UUID> commentsIDs = new HashSet<>();
}
