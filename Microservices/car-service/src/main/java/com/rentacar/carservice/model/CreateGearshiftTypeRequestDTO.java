package com.rentacar.carservice.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createGearshiftTypeRequestDTO", propOrder = {
        "gearshiftTypeID",
        "type",
        "numberOfGears"
}, namespace = "http://www.car.com/car")
public class CreateGearshiftTypeRequestDTO {

    private UUID gearshiftTypeID;

    private String type;

    private String numberOfGears;
}
