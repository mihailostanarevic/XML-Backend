package com.rentacar.rentservice.service;

import com.rentacar.rentservice.entity.Request;
import com.rentacar.rentservice.util.enums.RequestStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface IRequestService {

    Request createRequest();            // params

    Request createBundleRequest();      // params

    Request approveRejectRequest();     // params

    Request cancelRequest();            // params

    List<Request> requestHistory();     // params

    boolean rentACar(UUID carID);

    boolean changeCarStatus(UUID carID);

    RequestStatus changeRequestStatus(RequestStatus requestStatus);
}
