package com.rentacar.rentservice.repository;

import com.rentacar.rentservice.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IDiscountRepository extends JpaRepository<Discount, UUID> {

    Discount findOneByValue(double value);

    List<Discount> findAll();

}
