package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.dto.feignClient.UserMessageDTO;
import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.entity.User;
import com.rentacar.authentificationservice.repository.IUserRepository;
import com.rentacar.authentificationservice.services.IUserService;
import com.rentacar.authentificationservice.util.enums.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository _userRepository;

    public UserService(IUserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = _userRepository.findAllByDeleted(false);
        return users.stream()
                .map(user -> mapUserToUserResponse(user))
                .collect(Collectors.toList());
    }

    @Override
    public User getOneUser(UUID id) {
        return _userRepository.findOneById(id);
    }
  
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

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        return userResponse;
    }

    @Override
    public List<UserResponse> getCustomers() {
        List<User> users = _userRepository.findAllByDeleted(false);
        return users.stream()
                .filter(user -> user.getUserRole().equals(UserRole.SIMPLE_USER))
                .map(user -> mapUserToUserResponse(user))
                .collect(Collectors.toList());
    }

}
