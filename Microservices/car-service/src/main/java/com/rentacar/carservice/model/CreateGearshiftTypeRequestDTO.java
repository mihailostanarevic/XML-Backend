package com.rentacar.carservice.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createGearshiftTypeRequestDTO", propOrder = {
        "type",
        "numberOfGears"
})
public class CreateGearshiftTypeRequestDTO {

    private String type;

    private String numberOfGears;
}
