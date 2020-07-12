package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carClass", propOrder = {
        "carClassID",
        "name",
        "description",
        "deleted"
}, namespace = "http://www.car.com/car")
public class CarClass {

    private UUID carClassID;

    private String name;

    private String description;

    private boolean deleted;

    public UUID getCarClassID() {
        return carClassID;
    }

    public void setCarClassID(UUID carClassID) {
        this.carClassID = carClassID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
