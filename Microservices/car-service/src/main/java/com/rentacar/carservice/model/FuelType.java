package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fuelType", propOrder = {
        "fuelTypeID",
        "type",
        "tankCapacity",
        "gas",
        "deleted"
}, namespace = "http://www.car.com/car")
public class FuelType {

    private UUID fuelTypeID;

    private String type; //ili enum {diesel, benzine}

    private String tankCapacity; //ili enum {about 50l, about 60l, about 70l, about 80l, about 90l}

    private boolean gas;

    private boolean deleted;

    public UUID getFuelTypeID() {
        return fuelTypeID;
    }

    public void setFuelTypeID(UUID fuelTypeID) {
        this.fuelTypeID = fuelTypeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(String tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public boolean isGas() {
        return gas;
    }

    public void setGas(boolean gas) {
        this.gas = gas;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
