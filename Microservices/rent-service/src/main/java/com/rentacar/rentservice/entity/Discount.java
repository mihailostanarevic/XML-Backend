package com.rentacar.rentservice.entity;

import com.rentacar.rentservice.config.DateTimeConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discount extends BaseEntity {

    private double value;       // procenat popusta

    private DateTimeConfig dateOfPublication;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pricelist> pricelist;

    private boolean deleted;

}
