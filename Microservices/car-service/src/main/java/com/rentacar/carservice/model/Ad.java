package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ad", propOrder = {
        "adID",
        "carModel",
        "gearshiftType",
        "fuelType",
        "agentId",
        "limitedDistance",
        "availableKilometersPerRent",
        "kilometersTraveled",
        "seats",
        "cdw",
        "simpleUser"
}, namespace = "http://www.car.com/car")
public class Ad{

    private UUID adID;

    private String carModel;

    private String gearshiftType;

    private String fuelType;

    private UUID agentId;

    private boolean limitedDistance;

    private String availableKilometersPerRent;

    private String kilometersTraveled;

    private int seats;

    private boolean cdw;

    private boolean simpleUser;

    public UUID getAdID() {
        return adID;
    }

    public void setAdID(UUID adID) {
        this.adID = adID;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getGearshiftType() {
        return gearshiftType;
    }

    public void setGearshiftType(String gearshiftType) {
        this.gearshiftType = gearshiftType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public UUID getAgentId() {
        return agentId;
    }

    public void setAgentId(UUID agentId) {
        this.agentId = agentId;
    }

    public boolean isLimitedDistance() {
        return limitedDistance;
    }

    public void setLimitedDistance(boolean limitedDistance) {
        this.limitedDistance = limitedDistance;
    }

    public String getAvailableKilometersPerRent() {
        return availableKilometersPerRent;
    }

    public void setAvailableKilometersPerRent(String availableKilometersPerRent) {
        this.availableKilometersPerRent = availableKilometersPerRent;
    }

    public String getKilometersTraveled() {
        return kilometersTraveled;
    }

    public void setKilometersTraveled(String kilometersTraveled) {
        this.kilometersTraveled = kilometersTraveled;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isCdw() {
        return cdw;
    }

    public void setCdw(boolean cdw) {
        this.cdw = cdw;
    }

    public boolean isSimpleUser() {
        return simpleUser;
    }

    public void setSimpleUser(boolean simpleUser) {
        this.simpleUser = simpleUser;
    }
}
