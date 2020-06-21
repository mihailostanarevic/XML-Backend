package com.rentacar.authentificationservice.services;

import com.rentacar.authentificationservice.dto.feignClient.UserDTO;
import com.rentacar.authentificationservice.dto.feignClient.UserMessageDTO;

import java.util.UUID;

public interface IUserService {

    UserMessageDTO getUser(UUID id);
}
