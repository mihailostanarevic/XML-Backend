package com.rentacar.rentservice.dto;

import com.rentacar.rentservice.entity.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
public class RequestDTO {

    private UUID carID;

    private UUID agentID;

    private UUID customerID;

    private String receptionDate;

    private String pickUpDate;

    private String pickUpTime;

    private String returnDate;

    private String returnTime;

    private Address pickUpAddress;

    private boolean bundle;

}
