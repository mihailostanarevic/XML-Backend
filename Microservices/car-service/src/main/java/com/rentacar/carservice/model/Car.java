package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "car", propOrder = {
        "carID",
        "carModel",
        "gearshiftType",
        "fuelType",
        "adID",
        "kilometersTraveled",
        "deleted"
}, namespace = "http://www.car.com/car")
public class Car {

    private UUID carID;

    private UUID carModel;

    private UUID gearshiftType;

    private UUID fuelType;

    private UUID adID;

    private String kilometersTraveled;

    private boolean deleted;

    public UUID getCarID() {
        return carID;
    }

    public void setCarID(UUID carID) {
        this.carID = carID;
    }

    public UUID getCarModel() {
        return carModel;
    }

    public void setCarModel(UUID carModel) {
        this.carModel = carModel;
    }

    public UUID getGearshiftType() {
        return gearshiftType;
    }

    public void setGearshiftType(UUID gearshiftType) {
        this.gearshiftType = gearshiftType;
    }

    public UUID getFuelType() {
        return fuelType;
    }

    public void setFuelType(UUID fuelType) {
        this.fuelType = fuelType;
    }

    public UUID getAdID() {
        return adID;
    }

    public void setAdID(UUID adID) {
        this.adID = adID;
    }

    public String getKilometersTraveled() {
        return kilometersTraveled;
    }

    public void setKilometersTraveled(String kilometersTraveled) {
        this.kilometersTraveled = kilometersTraveled;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
