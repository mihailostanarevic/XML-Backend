package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comment", propOrder = {
        "commentID",
        "comment",
        "simpleUser",
        "adID",
        "status",
        "agent"
}, namespace = "http://www.car.com/car")
public class Comment {

    private UUID commentID;

    private String comment;

    private UUID simpleUser;

    private UUID adID;

    private String status;

    private UUID agent;
}
