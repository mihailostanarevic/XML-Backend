package com.rentacar.rentservice.repository;

import com.rentacar.rentservice.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IDiscountRepository extends JpaRepository<Discount, UUID> {

    Discount findOneByValue(double value);

    List<Discount> findAll();

}
