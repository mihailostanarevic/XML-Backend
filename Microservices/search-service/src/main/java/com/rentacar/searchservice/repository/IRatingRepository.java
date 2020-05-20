package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRatingRepository extends JpaRepository<Rating, UUID> {

}