package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.entity.User;
import com.rentacar.authentificationservice.entity.UserDetailsFactory;
import com.rentacar.authentificationservice.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository _userRepository;

    @Autowired
    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        final User user = _userRepository.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        return UserDetailsFactory.create(user);
    }

}
