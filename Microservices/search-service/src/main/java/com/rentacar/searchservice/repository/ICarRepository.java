package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICarRepository extends JpaRepository<Car, UUID> {

}