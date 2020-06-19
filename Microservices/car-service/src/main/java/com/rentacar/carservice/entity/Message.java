package com.rentacar.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Message extends BaseEntity {

    private String text;

    private UUID userSender;

    private UUID userReceiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Ad ad;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MessageCarAccessories> messageCarAccessories = new HashSet<MessageCarAccessories>();

    private boolean seen;

    private LocalDate dateSent;

    private LocalTime timeSent;

    public Message(){
        this.dateSent = LocalDate.now();
        this.timeSent = LocalTime.now();
        this.seen = false;
    }
}
