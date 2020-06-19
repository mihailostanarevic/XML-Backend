package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAdRepository extends JpaRepository<Ad, UUID> {
    Ad findOneById(UUID id);

    List<Ad> findAllByDeleted(boolean deleted);
}
