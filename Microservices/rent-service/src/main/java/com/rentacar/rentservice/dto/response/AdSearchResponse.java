package com.rentacar.rentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdSearchResponse {

    private UUID adID;          //

    private boolean limitedDistance;

    private String distance;

    private int seats;

    private boolean cdw;

    private LocalDate creationDate;

    private List<PhotoResponse> photos;

    private double avgRating;
}
