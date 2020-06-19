package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.entity.CarAccessories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICarAccessoriesRepository extends JpaRepository<CarAccessories, UUID> {
}
