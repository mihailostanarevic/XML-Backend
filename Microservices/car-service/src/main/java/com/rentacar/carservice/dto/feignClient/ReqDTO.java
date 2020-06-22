package com.rentacar.carservice.dto.feignClient;

import com.rentacar.carservice.util.enums.RequestStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ReqDTO {

    private UUID id;

    private SimpleUserDTO customer;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDate receptionDate;

    private String pickUpAddress;

    private boolean deleted;

    private List<RequestAdDTO> requestAds = new ArrayList<RequestAdDTO>();
}
