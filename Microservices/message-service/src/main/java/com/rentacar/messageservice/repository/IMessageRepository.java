package com.rentacar.messageservice.repository;

import com.rentacar.messageservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMessageRepository extends JpaRepository<Message, UUID> {
}
