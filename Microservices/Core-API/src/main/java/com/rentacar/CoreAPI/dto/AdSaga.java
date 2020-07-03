package com.rentacar.CoreAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdSaga {

    private CarDetails carDetails;

    private AdDetails adDetails;

    private PhotoDetailsList photosDetails;

}
