package com.rentacar.rentservice.entity;

import com.rentacar.rentservice.util.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Request extends BaseEntity {

    @Column(name = "customer_id")
    private UUID customerID;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDate receptionDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private String pickUpAddress;

    private boolean deleted;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RequestAd> requestAds = new HashSet<RequestAd>();

    public Request() {
        this.receptionDate = LocalDate.now();
    }

}
