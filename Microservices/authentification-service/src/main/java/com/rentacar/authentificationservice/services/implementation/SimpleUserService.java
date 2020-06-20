package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.entity.Agent;
import com.rentacar.authentificationservice.entity.SimpleUser;
import com.rentacar.authentificationservice.entity.User;
import com.rentacar.authentificationservice.repository.IAgentRepository;
import com.rentacar.authentificationservice.repository.ISimpleUserRepository;
import com.rentacar.authentificationservice.repository.IUserRepository;
import com.rentacar.authentificationservice.services.ISimpleUserService;
import com.rentacar.authentificationservice.util.enums.RequestStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SimpleUserService implements ISimpleUserService {

    private final ISimpleUserRepository _simpleUserRepository;
    private final IUserRepository _userRepository;

    public SimpleUserService(ISimpleUserRepository simpleUserRepository, IUserRepository userRepository) {
        _simpleUserRepository = simpleUserRepository;
        _userRepository = userRepository;
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
    }

    @Override
    public UUID getIDByUsername(String username) {
        User user = _userRepository.findOneByUsername(username);
        if(user != null) {
            return _simpleUserRepository.findOneByUser(user).getId();
        }
        return null;
    }
}
