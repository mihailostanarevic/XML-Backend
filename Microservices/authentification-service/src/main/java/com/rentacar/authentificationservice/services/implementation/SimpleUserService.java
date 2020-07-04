package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.dto.client.CustomerResponse;
import com.rentacar.authentificationservice.dto.client.UUIDResponse;
import com.rentacar.authentificationservice.dto.response.SimpleUserAgentIdResponse;
import com.rentacar.authentificationservice.entity.Agent;
import com.rentacar.authentificationservice.entity.Authority;
import com.rentacar.authentificationservice.dto.feignClient.SimpleUserDTO;
import com.rentacar.authentificationservice.dto.feignClient.UserDTO;
import com.rentacar.authentificationservice.entity.SimpleUser;
import com.rentacar.authentificationservice.entity.User;
import com.rentacar.authentificationservice.repository.IAgentRepository;
import com.rentacar.authentificationservice.repository.IAuthorityRepository;
import com.rentacar.authentificationservice.repository.ISimpleUserRepository;
import com.rentacar.authentificationservice.repository.IUserRepository;
import com.rentacar.authentificationservice.services.ISimpleUserService;
import com.rentacar.authentificationservice.util.enums.RequestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SimpleUserService implements ISimpleUserService {

    private final ISimpleUserRepository _simpleUserRepository;
    private final IUserRepository _userRepository;
    private final IAgentRepository _agentRepository;
    private final IAuthorityRepository _authorityRepository;
    private final Logger logger = LoggerFactory.getLogger("Auth service app: " + SimpleUserService.class);

    public SimpleUserService(ISimpleUserRepository simpleUserRepository, IUserRepository userRepository, IAgentRepository agentRepository, IAuthorityRepository authorityRepository) {
        _simpleUserRepository = simpleUserRepository;
        _userRepository = userRepository;
        _agentRepository = agentRepository;
        _authorityRepository = authorityRepository;
    }

    @Override
    public void blockSimpleUserByAdmin(UUID id) throws Exception {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
//        simpleUser.setBlocked(true);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public void unblockSimpleUserByAdmin(UUID id) throws Exception {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
//        simpleUser.setBlocked(false);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public void activateSimpleUserByAdmin(UUID id) throws Exception {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        simpleUser.setRequestStatus(RequestStatus.APPROVED);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public void deactivateSimpleUserByAdmin(UUID id) throws Exception {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        simpleUser.setRequestStatus(RequestStatus.DENIED);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public void deleteSimpleUserByAdmin(UUID id) throws Exception {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        simpleUser.getUser().setDeleted(true);
        _userRepository.save(simpleUser.getUser());
        _simpleUserRepository.save(simpleUser);
        logger.info("User with id: " + id + " has been deleted");
    }

    @Override
    public UUIDResponse getIDByUsername(String username) {
        User user = _userRepository.findOneByUsername(username);
        if(user != null) {
            SimpleUser simpleUser = _simpleUserRepository.findOneByUser(user);
            if(simpleUser != null) {
                UUID id = simpleUser.getId();
                UUIDResponse retUuidResponse = new UUIDResponse();
                retUuidResponse.setId(id);
                return retUuidResponse;
            }
        }
        return null;
    }

    @Override
    public SimpleUserDTO getSimpleUser(UUID id) {
        SimpleUserDTO retVal = new SimpleUserDTO();
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        if (simpleUser == null) {
            return retVal;
        }
        UserDTO userDTO = new UserDTO(simpleUser.getUser().getId(), simpleUser.getUser().getUserRole());
        retVal.setUser(userDTO);
        retVal.setId(simpleUser.getId());
        retVal.setFirstName(simpleUser.getFirstName());
        retVal.setLastName(simpleUser.getLastName());
        retVal.setSsn(simpleUser.getSsn());
        retVal.setAddress(simpleUser.getAddress());

        return retVal;
    }

    public void addUserRole(UUID simpleUserID, String userRole) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(simpleUserID);
        User user = simpleUser.getUser();
        Authority authority = _authorityRepository.findByName(userRole);
        user.getRoles().add(authority);
        logger.info("A new role has been added to user: " + user.getId());
        _userRepository.save(user);
    }

    @Override
    public CustomerResponse getCustomerByID(UUID id) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        if(simpleUser != null) {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setId(simpleUser.getId());
            customerResponse.setAddress(simpleUser.getAddress());
            customerResponse.setFirstName(simpleUser.getFirstName());
            customerResponse.setLastName(simpleUser.getLastName());
            customerResponse.setSsn(simpleUser.getSsn());
            customerResponse.setUsername(simpleUser.getUser().getUsername());
            customerResponse.setUserRole(simpleUser.getUser().getUserRole().toString());
            return customerResponse;
        } else {
            return null;
        }
    }

    @Override
    public SimpleUserAgentIdResponse getAgentIDFromSimpleUser(UUID simpleUserId) {
        Agent agent = _agentRepository.findOneBySimpleUserId(simpleUserId);
        return new SimpleUserAgentIdResponse(agent.getId());
    }


}
