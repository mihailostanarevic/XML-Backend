package com.rentacar.rentservice.dto;

import com.rentacar.rentservice.entity.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class RequestDTO {

    private UUID carID;

    private UUID agentID;

    private UUID customerID;

    private String receptionDate;

    private String pickUpDate;      // format -> "2016-06-12"

    private String pickUpTime;      // format -> "06:30"
    
    private String returnDate;

    private String returnTime;

    private Address pickUpAddress;

    private boolean bundle;

}
