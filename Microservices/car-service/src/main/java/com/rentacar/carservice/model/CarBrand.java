package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carBrand", propOrder = {
        "carBrandID",
        "name",
        "country",
        "deleted"
}, namespace = "http://www.car.com/car")
public class CarBrand {

    private UUID carBrandID;

    private String name;

    private String country;

    private boolean deleted;

    public UUID getCarBrandID() {
        return carBrandID;
    }

    public void setCarBrandID(UUID carBrandID) {
        this.carBrandID = carBrandID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
