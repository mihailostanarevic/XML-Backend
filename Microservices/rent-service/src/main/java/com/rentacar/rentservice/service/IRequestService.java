package com.rentacar.rentservice.service;

import com.rentacar.rentservice.entity.Request;
import com.rentacar.rentservice.util.enums.RequestStatus;

import java.util.List;
import java.util.UUID;

public interface IRequestService {

    Request createRequest();

    Request createBundleRequest();

    Request approveRejectRequest();

    Request cancelRequest();

    List<Request> requestHistory();

    boolean rentACar(UUID carID);

    boolean changeCarStatus(UUID carID);

    RequestStatus changeRequestStatus(RequestStatus requestStatus);
}
