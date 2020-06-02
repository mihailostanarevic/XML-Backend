package com.rentacar.rentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;

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

    private LocalDate dateOfPublication;

    private boolean deleted;

}
