package com.rentacar.carservice.model;

import com.rentacar.carservice.entity.BaseEntity;
import com.rentacar.carservice.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gearshiftType", propOrder = {
        "type",
        "numberOfGears",
        "cars",
        "deleted"
})
public class GearshiftType extends BaseEntity {

    private String type; //ili enum {automatic, manual, semiautomatic}

    private String numberOfGears; //ili enum {four, five, six , seven, eight, nine, ten} poslednjih nekoliko se odnose na automatike

    @OneToMany(mappedBy = "gearshiftType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    private boolean deleted;
}
