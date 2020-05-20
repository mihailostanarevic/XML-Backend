package com.rentacar.searchservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SearchParametersDTO {

    private String takeoverAddress;

    @JsonFormat(pattern = "hh:mm dd-MM-yyyy")
    private Date dateOfTakeover;

    @JsonFormat(pattern = "hh:mm dd-MM-yyyy")
    private Date returnDate;
}
