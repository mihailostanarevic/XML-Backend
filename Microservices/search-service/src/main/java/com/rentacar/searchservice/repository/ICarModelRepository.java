package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICarModelRepository extends JpaRepository<CarModel, UUID> {

    CarModel findOneByName(String name);

}
