package com.rentacar.rentservice.entity;

import com.rentacar.rentservice.config.DateTimeConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discount extends BaseEntity {

    private double value;       // procenat popusta

    private DateTimeConfig dateOfPublication;

    @OneToMany(mappedBy = "pricelist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Pricelist pricelist;

    private boolean deleted;

}
