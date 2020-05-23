package com.rentacar.searchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarAccessories extends BaseEntity {

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ad_car_accessories",
            joinColumns = { @JoinColumn(name = "ad_id") },
            inverseJoinColumns = { @JoinColumn(name = "car_accessories_id") }
    )
    private List<Ad> ads = new ArrayList<>();

    private boolean deleted;
}
