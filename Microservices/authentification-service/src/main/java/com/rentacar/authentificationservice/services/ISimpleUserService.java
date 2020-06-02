package com.rentacar.authentificationservice.services;

import java.util.UUID;

public interface ISimpleUserService {

    void blockSimpleUserByAdmin(UUID id) throws Exception;

    void unblockSimpleUserByAdmin(UUID id) throws Exception;

    void activateSimpleUserByAdmin(UUID id) throws Exception;

    void deactivateSimpleUserByAdmin(UUID id) throws Exception;

    void deleteSimpleUserByAdmin(UUID id) throws Exception;
}
