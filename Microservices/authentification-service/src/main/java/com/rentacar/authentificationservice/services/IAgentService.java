package com.rentacar.authentificationservice.services;

import java.util.UUID;

public interface IAgentService {

    void blockAgentByAdmin(UUID id) throws Exception;

    void unblockAgentByAdmin(UUID id) throws Exception;

    void activateAgentByAdmin(UUID id) throws Exception; //approving when agent register

    void deactivateAgentByAdmin(UUID id) throws Exception;

    void deleteAgentByAdmin(UUID id) throws Exception;
}
