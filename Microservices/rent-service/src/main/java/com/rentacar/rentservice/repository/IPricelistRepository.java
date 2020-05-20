package com.rentacar.rentservice.repository;

import com.rentacar.rentservice.entity.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public interface IPricelistRepository extends JpaRepository<Pricelist, UUID> {

}
