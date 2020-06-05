package com.rentacar.authentificationservice.repository;

import com.rentacar.authentificationservice.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);

    Authority findOneByName(String name);

    Authority findOneById(Long id);
}
