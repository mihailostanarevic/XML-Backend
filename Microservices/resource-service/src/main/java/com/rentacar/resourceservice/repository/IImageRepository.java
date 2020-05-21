package com.rentacar.resourceservice.repository;

import com.rentacar.resourceservice.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IImageRepository extends JpaRepository<Image, UUID> {


}
