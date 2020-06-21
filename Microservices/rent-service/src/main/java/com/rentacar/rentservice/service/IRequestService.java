package com.rentacar.rentservice.service;

import com.rentacar.rentservice.dto.feignClient.RequestDTO;
import com.rentacar.rentservice.dto.request.RequestRequest;
import com.rentacar.rentservice.entity.Request;
import com.rentacar.rentservice.util.enums.RequestStatus;

import java.util.List;
import java.util.UUID;

public interface IRequestService {

    void processRequests(List<RequestRequest> requestList);

    Request createRequest(RequestRequest requestRequest);

    Request createBundleRequest(List<RequestRequest> requestList);

    void changeAdAvailability(RequestRequest request);

    boolean rentACar(UUID carID);

    boolean changeCarStatus(UUID carID);

    RequestStatus changeRequestStatus(RequestStatus requestStatus);

    List<RequestDTO> getRequestsByStatus(RequestStatus status);
}
