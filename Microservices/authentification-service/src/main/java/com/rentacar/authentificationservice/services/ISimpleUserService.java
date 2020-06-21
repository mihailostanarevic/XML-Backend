package com.rentacar.authentificationservice.services;

import com.rentacar.authentificationservice.dto.client.UUIDResponse;

import java.util.UUID;

public interface ISimpleUserService {

    void blockSimpleUserByAdmin(UUID id) throws Exception;

    void unblockSimpleUserByAdmin(UUID id) throws Exception;

    void activateSimpleUserByAdmin(UUID id) throws Exception;

    void deactivateSimpleUserByAdmin(UUID id) throws Exception;

    void deleteSimpleUserByAdmin(UUID id) throws Exception;

    UUIDResponse getIDByUsername(String username);

    void addUserRole(UUID simpleUserID, String userRole);
}
