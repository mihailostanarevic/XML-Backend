package com.rentacar.carservice.repository;

import com.rentacar.carservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IMessageRepository extends JpaRepository<Message, UUID> {

    Message findOneById(UUID id);

    List<Message> findAllByUserReceiver(UUID id);
}
