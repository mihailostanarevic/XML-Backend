package com.rentacar.CoreAPI.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PhotoDetailsList {

    private List<PhotoDetails> photosDetails = new ArrayList<>();

}
