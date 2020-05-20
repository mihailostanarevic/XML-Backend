package com.rentacar.carservice.entity;

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
public class Car extends BaseEntity {

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> carModels;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gear_shift_id")
    private GearshiftType gearshiftType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_type_id")
    private FuelType fuelType;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "car_accessories_car",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "car_accessories_id")
    )
    List<CarAccessories> carAccessories = new ArrayList<>();

    private String kilometersTraveled;

    private boolean deleted;
}
