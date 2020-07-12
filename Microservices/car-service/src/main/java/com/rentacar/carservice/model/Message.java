package com.rentacar.carservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "message", propOrder = {
        "messageID",
        "text",
        "userSender",
        "userReceiver",
        "adID",
        "seen",
        "dateSent",
        "timeSent",
}, namespace = "http://www.car.com/car")
public class Message {

    private UUID messageID;

    private String text;

    private UUID userSender;

    private UUID userReceiver;

    private UUID adID;

    private boolean seen;

    private String dateSent;

    private String timeSent;
}
