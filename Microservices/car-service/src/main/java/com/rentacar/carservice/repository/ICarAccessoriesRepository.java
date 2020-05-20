package com.rentacar.carservice.repository;

import com.rentacar.carservice.entity.CarAccessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICarAccessoriesRepository extends JpaRepository<CarAccessories, UUID> {

}
