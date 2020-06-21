package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.dto.feignClient.UserDTO;
import com.rentacar.authentificationservice.dto.feignClient.UserMessageDTO;
import com.rentacar.authentificationservice.entity.Agent;
import com.rentacar.authentificationservice.entity.User;
import com.rentacar.authentificationservice.repository.IAgentRepository;
import com.rentacar.authentificationservice.repository.IUserRepository;
import com.rentacar.authentificationservice.services.IUserService;
import com.rentacar.authentificationservice.util.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;

    @Override
    public UserMessageDTO getUser(UUID id) {
        User user = _userRepository.findOneById(id);
        UserMessageDTO retVal = new UserMessageDTO();
        retVal.setUserRole(user.getUserRole());
        retVal.setId(user.getId());
        if(user.getUserRole().equals(UserRole.AGENT)){
            retVal.setName(user.getAgent().getName());
            retVal.setSubjectID(user.getAgent().getId());
        }else {
            retVal.setName(user.getSimpleUser().getFirstName());
            retVal.setLastName(user.getSimpleUser().getLastName());
            retVal.setSubjectID(user.getSimpleUser().getId());
        }
        return retVal;
    }


}
