package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carCarAccessories", propOrder = {
        "carID",
        "carAccessoryID"
}, namespace = "http://www.car.com/car")
public class CarCarAccessories {

    private UUID carID;

    private UUID carAccessoryID;
}
