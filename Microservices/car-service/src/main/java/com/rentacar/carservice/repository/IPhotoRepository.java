package com.rentacar.carservice.repository;

import com.rentacar.carservice.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPhotoRepository extends JpaRepository<Photo, UUID> {
}
