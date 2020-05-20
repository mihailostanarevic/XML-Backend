package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.entity.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICarClassRepository extends JpaRepository<CarClass, UUID> {

}
