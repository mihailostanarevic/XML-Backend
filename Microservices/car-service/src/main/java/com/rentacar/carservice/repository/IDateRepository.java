package com.rentacar.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDateRepository extends JpaRepository<Date, UUID> {

}
