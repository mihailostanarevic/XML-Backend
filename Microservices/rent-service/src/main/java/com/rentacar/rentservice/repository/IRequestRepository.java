package com.rentacar.rentservice.repository;

import com.rentacar.rentservice.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IRequestRepository extends JpaRepository<Request, UUID> {

    Request findOneById(UUID id);

    List<Request> findAllByCarID(UUID id);

    List<Request> findAllByCustomerID(UUID id);

    List<Request> findAllByAgentID(UUID id);

}
