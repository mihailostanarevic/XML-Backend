package com.rentacar.searchservice.dto.feignClient;

import com.rentacar.searchservice.util.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private UUID id;

    private SimpleUserDTO customer;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDate receptionDate;

    private String pickUpAddress;

    private boolean deleted;

    private List<RequestAdDTO> requestAds = new ArrayList<RequestAdDTO>();
}
