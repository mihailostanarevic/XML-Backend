package com.rentacar.authentificationservice.services;

import com.rentacar.authentificationservice.dto.feignClient.SimpleUserDTO;

import java.util.UUID;

public interface ISimpleUserService {

    void blockSimpleUserByAdmin(UUID id) throws Exception;

    void unblockSimpleUserByAdmin(UUID id) throws Exception;

    void activateSimpleUserByAdmin(UUID id) throws Exception;

    void deactivateSimpleUserByAdmin(UUID id) throws Exception;

    void deleteSimpleUserByAdmin(UUID id) throws Exception;

    UUID getIDByUsername(String username);

    SimpleUserDTO getSimpleUser(UUID id);
}
