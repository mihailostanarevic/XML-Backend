package com.rentacar.carservice.repository;

import com.rentacar.carservice.entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFuelTypeRepository extends JpaRepository<FuelType, UUID> {

    FuelType findOneById(UUID id);

    List<FuelType> findAllByDeleted(boolean deleted);

    FuelType findOneByType(String type);
}
