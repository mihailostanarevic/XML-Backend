package com.rentacar.rentservice.repository;

import com.rentacar.rentservice.entity.Request;
import com.rentacar.rentservice.util.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
@Repository
public interface IRequestRepository extends JpaRepository<Request, UUID> {

    Request findOneById(UUID id);

    List<Request> findAllByStatus(RequestStatus requestStatus);

    List<Request> findAllByCustomerID(UUID id);

    List<Request> findAllByDeleted(boolean deleted);

    List<Request> findAllByCustomerIDAndStatus(UUID id, RequestStatus status);

}
