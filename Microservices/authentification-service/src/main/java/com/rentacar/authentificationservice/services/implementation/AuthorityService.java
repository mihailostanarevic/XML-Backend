package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.entity.Authority;
import com.rentacar.authentificationservice.repository.IAuthorityRepository;
import com.rentacar.authentificationservice.services.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AuthorityService implements IAuthorityService {

    @Autowired
    private IAuthorityRepository _authorityRepository;

    public List<Authority> findByname(String name) {
        Authority auth = this._authorityRepository.findByName(name);
        ArrayList<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }
}
