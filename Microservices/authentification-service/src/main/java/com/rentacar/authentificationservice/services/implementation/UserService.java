package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.entity.User;
import com.rentacar.authentificationservice.repository.IUserRepository;
import com.rentacar.authentificationservice.services.IUserService;
import com.rentacar.authentificationservice.util.enums.UserRole;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public User getUser(UUID id) {
        return _userRepository.findOneById(id);
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
