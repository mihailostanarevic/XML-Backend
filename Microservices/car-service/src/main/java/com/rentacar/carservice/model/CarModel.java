package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carModel", propOrder = {
        "carModelID",
        "name",
        "carBrand",
        "carClass",
        "deleted"
}, namespace = "http://www.car.com/car")
public class CarModel {

    private UUID carModelID;

    private String name;

    private UUID carBrand;

    private UUID carClass;

    private boolean deleted;

    public UUID getCarModelID() {
        return carModelID;
    }

    public void setCarModelID(UUID carModelID) {
        this.carModelID = carModelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(UUID carBrand) {
        this.carBrand = carBrand;
    }

    public UUID getCarClass() {
        return carClass;
    }

    public void setCarClass(UUID carClass) {
        this.carClass = carClass;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
