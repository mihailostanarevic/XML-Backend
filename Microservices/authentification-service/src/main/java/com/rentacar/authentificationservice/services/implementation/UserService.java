package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.dto.feignClient.UserMessageDTO;
import com.rentacar.authentificationservice.dto.response.RoleResponse;
import com.rentacar.authentificationservice.dto.response.UserDetailsResponse;
import com.rentacar.authentificationservice.dto.response.UserResponse;
import com.rentacar.authentificationservice.entity.Authority;
import com.rentacar.authentificationservice.entity.Permission;
import com.rentacar.authentificationservice.entity.User;
import com.rentacar.authentificationservice.repository.IAuthorityRepository;
import com.rentacar.authentificationservice.repository.IUserRepository;
import com.rentacar.authentificationservice.services.IUserService;
import com.rentacar.authentificationservice.util.enums.UserRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository _userRepository;
    private final IAuthorityRepository _authorityRepository;

    public UserService(IUserRepository userRepository, IAuthorityRepository authorityRepository) {
        _userRepository = userRepository;
        _authorityRepository = authorityRepository;
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

    @Override
    public List<RoleResponse> getPermissions(UUID userId) {
        User user = _userRepository.findOneById(userId);
        List<RoleResponse> roleListOfUser = new ArrayList<>();
        for (Authority authority : user.getRoles()) {
            RoleResponse roleResponse = mapAuthorityToPermissionResponse(authority);
            roleListOfUser.add(roleResponse);
        }
        return roleListOfUser;
    }

    @Override
    public List<UserDetailsResponse> getUsers() {
        List<User> userList = _userRepository.findAllByDeleted(false);
        List<UserDetailsResponse> userDetailsResponseList = new ArrayList<>();
        for (User user : userList) {
            if(!user.getUserRole().equals(UserRole.ADMIN)) {
                userDetailsResponseList.add(mapUserToUserDetailsResponse(user));
            }
        }

        return userDetailsResponseList;
    }

    @Override
    public List<UserDetailsResponse> deleteRole(Long roleId, UUID userId) {
        User user = _userRepository.findOneById(userId);
        Authority authority = _authorityRepository.findOneById(roleId);
        user.getRoles().remove(authority);
        _userRepository.save(user);
        return getUsers();
    }

    private UserDetailsResponse mapUserToUserDetailsResponse(User user) {
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        userDetailsResponse.setId(user.getId());
        userDetailsResponse.setUsername(user.getUsername());
        userDetailsResponse.setUserRole(user.getUserRole().toString());
        setFirstAndLastName(userDetailsResponse, user);
        userDetailsResponse.setRoleList(getPermissions(user.getId()));
        return userDetailsResponse;
    }

    private void setFirstAndLastName(UserDetailsResponse userDetailsResponse, User user) {
        if(user.getAgent() != null) {
            userDetailsResponse.setName(user.getAgent().getName());
        } else {
            userDetailsResponse.setName(user.getSimpleUser().getFirstName() + " " + user.getSimpleUser().getLastName());
        }
    }

    private RoleResponse mapAuthorityToPermissionResponse(Authority authority) {
        List<String> permissionListOfRole = new ArrayList<>();
        for (Permission permission : authority.getPermissions()) {
            permissionListOfRole.add(permission.getName());
        }
        return new RoleResponse(authority.getId(), authority.getName(), permissionListOfRole);
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
