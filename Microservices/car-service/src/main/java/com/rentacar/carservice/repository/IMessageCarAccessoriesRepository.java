package com.rentacar.carservice.repository;

import com.rentacar.carservice.entity.MessageCarAccessories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IMessageCarAccessoriesRepository extends JpaRepository<MessageCarAccessories, UUID> {
}
