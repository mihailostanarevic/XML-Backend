package com.rentacar.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuelType {

    @Id
    private UUID id;

    private String type; //ili enum {diesel, benzine}

    private String tankCapacity; //ili enum {about 50l, about 60l, about 70l, about 80l, about 90l}

    private boolean gas;

    @OneToMany(mappedBy = "fuelType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    private boolean deleted;
}
