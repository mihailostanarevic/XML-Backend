package com.rentacar.authentificationservice.services;

import com.rentacar.authentificationservice.entity.Authority;

import java.util.List;

public interface IAuthorityService {

    List<Authority> findByname(String name);
}

