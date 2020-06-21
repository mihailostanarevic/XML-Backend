package com.rentacar.authentificationservice.services;

import com.rentacar.authentificationservice.dto.feignClient.AgentDTO;
import com.rentacar.authentificationservice.dto.feignClient.UserMessageDTO;
import com.rentacar.authentificationservice.dto.response.AgentRequests;
import com.rentacar.authentificationservice.dto.response.AgentResponse;
import com.rentacar.authentificationservice.util.enums.RequestStatus;

import java.util.Collection;
import java.util.UUID;

public interface IAgentService {

    void blockAgentByAdmin(UUID id) throws Exception;

    void unblockAgentByAdmin(UUID id) throws Exception;

    void activateAgentByAdmin(UUID id) throws Exception; //approving when agent register

    void deactivateAgentByAdmin(UUID id) throws Exception;

    void deleteAgentByAdmin(UUID id) throws Exception;

    String getAgentAddress(UUID id);

  // TODO pogledati 
    AgentResponse getAgent(UUID id);
  
    AgentDTO getAgent(UUID id);

    UserMessageDTO getUserFromAgent(UUID id);
}
