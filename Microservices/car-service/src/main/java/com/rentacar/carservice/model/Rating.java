package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rating", propOrder = {
        "ratingID",
        "grade",
        "simpleUser",
        "adID"
}, namespace = "http://www.car.com/car")
public class Rating {

    private UUID ratingID;

    private String grade;               //rating

    private UUID simpleUser;

    private UUID adID;
}
