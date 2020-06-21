package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.dto.response.AgentRequests;
import com.rentacar.authentificationservice.dto.response.AgentResponse;
import com.rentacar.authentificationservice.entity.Agent;
import com.rentacar.authentificationservice.repository.IAgentRepository;
import com.rentacar.authentificationservice.repository.IUserRepository;
import com.rentacar.authentificationservice.services.IAgentService;
import com.rentacar.authentificationservice.util.enums.RequestStatus;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Service
public class AgentService implements IAgentService {

    private final IAgentRepository _agentRepository;

    private final IUserRepository _userRepository;

    public AgentService(IAgentRepository agentRepository, IUserRepository userRepository) {
        _agentRepository = agentRepository;
        _userRepository = userRepository;
    }

    @Override
    public void blockAgentByAdmin(UUID id) throws Exception {
        Agent agent = _agentRepository.findOneById(id);
//        agent.setBlocked(true);
        _agentRepository.save(agent);
    }

    @Override
    public void unblockAgentByAdmin(UUID id) throws Exception {
        Agent agent = _agentRepository.findOneById(id);
//        agent.setBlocked(false);
        _agentRepository.save(agent);
    }

    @Override
    public void activateAgentByAdmin(UUID id) throws Exception {
        Agent agent = _agentRepository.findOneById(id);
//        agent.setRequestStatus(RequestStatus.APPROVED);
        _agentRepository.save(agent);
    }

    @Override
    public void deactivateAgentByAdmin(UUID id) throws Exception {
        Agent agent = _agentRepository.findOneById(id);
//        agent.setRequestStatus(RequestStatus.DENIED);
        _agentRepository.save(agent);
    }

    @Override
    public void deleteAgentByAdmin(UUID id) throws Exception {
        Agent agent = _agentRepository.findOneById(id);
        agent.getUser().setDeleted(true);
        _userRepository.save(agent.getUser());
        _agentRepository.save(agent);
    }

    @Override
    public String getAgentAddress(UUID id) {
        return _agentRepository.findOneById(id).getAddress();
    }

    @Override
    public AgentResponse getAgent(UUID id) {
        return mapAgentToAgentResponse(_agentRepository.findOneById(id));
    }

    private AgentResponse mapAgentToAgentResponse(Agent agent) {
        AgentResponse agentResponse = new AgentResponse();
        agentResponse.setId(agent.getId());
        agentResponse.setAddress(agent.getAddress());
        agentResponse.setBankAccountNumber(agent.getBankAccountNumber());
        agentResponse.setDeleted(agent.getUser().isDeleted());
        agentResponse.setName(agent.getName());
        agentResponse.setTin(agent.getTin());
        agentResponse.setUsername(agent.getUser().getUsername());
        return agentResponse;
    }

}
