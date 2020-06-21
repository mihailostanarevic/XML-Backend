package com.rentacar.carservice.dto.feignClient;

import com.rentacar.carservice.util.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessageDTO {

    private UUID id;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String name;

    private String lastName;

    private UUID subjectID;
}
