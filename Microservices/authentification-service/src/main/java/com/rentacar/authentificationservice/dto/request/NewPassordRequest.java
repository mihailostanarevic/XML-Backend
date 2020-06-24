package com.rentacar.authentificationservice.dto.request;

import lombok.Data;

@Data
public class NewPassordRequest {

    private String password;

    private String rePassword;
}
