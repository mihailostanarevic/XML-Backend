package com.rentacar.rentservice.repository;

import com.rentacar.rentservice.entity.Discount;
import com.rentacar.rentservice.entity.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public interface IPricelistRepository extends JpaRepository<Pricelist, UUID> {

    Pricelist findOneById(UUID id);

    List<Pricelist> findAllByDiscount(Discount discount);

    List<Pricelist> findAllByPrice(double price);

}
