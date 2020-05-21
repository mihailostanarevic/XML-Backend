package com.rentacar.rentservice.entity;

import com.rentacar.rentservice.config.DateTimeConfig;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pricelist extends BaseEntity {

    private double price;

    private double pricePerKilometer;

    private double priceCollisionDamageWaiver;

    private double priceOverdraft;      // cena prekoracenja

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private Discount discount;

//    private List<UUID> carGroup;

    private int duration;

    private DateTimeConfig dateOfPublication;

    private boolean deleted;

}
