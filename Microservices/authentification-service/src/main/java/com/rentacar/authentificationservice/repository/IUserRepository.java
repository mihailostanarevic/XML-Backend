package com.rentacar.authentificationservice.repository;

import com.rentacar.authentificationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {

    User findOneById(UUID id);

    User findOneByUsername(String username);

    List<User> findAllByDeleted(boolean deleted);

}