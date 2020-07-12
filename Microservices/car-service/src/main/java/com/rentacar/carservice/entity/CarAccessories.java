package com.rentacar.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarAccessories{

    @Id
    private UUID id;

    private String description;

    @ManyToMany(mappedBy = "carAccessories")
    private List<Car> cars = new ArrayList<>();

    @OneToMany(mappedBy = "car_accessory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MessageCarAccessories> messageCarAccessories = new HashSet<MessageCarAccessories>();

    private boolean deleted;
}
