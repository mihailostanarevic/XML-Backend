package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAddressRepository extends JpaRepository<Address, UUID> {
}
