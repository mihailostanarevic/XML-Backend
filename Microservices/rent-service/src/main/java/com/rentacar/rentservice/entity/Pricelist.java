package com.rentacar.rentservice.entity;

import com.rentacar.rentservice.config.DateTimeConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
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

    private double priceOverdraft;

    private Discount discount;

    private List<UUID> carGroup;

    private int duration;       // 1-3 dana, 4-7 dana, 8-15, 16-25, 26+

    private DateTimeConfig dateOfPublication;

    private boolean deleted;

}
