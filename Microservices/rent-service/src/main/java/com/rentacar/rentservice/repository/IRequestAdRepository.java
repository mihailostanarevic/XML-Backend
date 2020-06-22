package com.rentacar.rentservice.repository;

import com.rentacar.rentservice.entity.Request;
import com.rentacar.rentservice.entity.RequestAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public interface IRequestAdRepository extends JpaRepository<RequestAd, UUID> {

    RequestAd findOneById(UUID id);

    List<RequestAd> findAllByAdID(UUID adID);

    List<RequestAd> findAllByRequest(Request request);

    List<RequestAd> findAllByRequest_Id(UUID id);
}
