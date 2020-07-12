package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carAccessories", propOrder = {
        "carAccessoriesID",
        "description",
        "deleted"
}, namespace = "http://www.car.com/car")
public class CarAccessories {

    private UUID carAccessoriesID;

    private String description;

    private boolean deleted;

    public UUID getCarAccessoriesID() {
        return carAccessoriesID;
    }

    public void setCarAccessoriesID(UUID carAccessoriesID) {
        this.carAccessoriesID = carAccessoriesID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
