package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFuelTypeRepository extends JpaRepository<FuelType, UUID> {

}
