package com.rentacar.rentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponse {

    private String name;

    private String type;

    private byte[] picByte;

}
