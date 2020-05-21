package com.rentacar.rentservice.service.implementation;

import com.rentacar.rentservice.entity.Request;
import com.rentacar.rentservice.repository.IRequestRepository;
import com.rentacar.rentservice.service.IRequestService;
import com.rentacar.rentservice.util.enums.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class RequestService implements IRequestService {

    @Autowired
    IRequestRepository _requestRepository;

    @Override
    public Request createRequest() {
        return null;
    }

    @Override
    public Request createBundleRequest() {
        return null;
    }

    @Override
    public Request approveRejectRequest() {
        return null;
    }

    @Override
    public Request cancelRequest() {
        return null;
    }

    @Override
    public List<Request> requestHistory() {
        return null;
    }

    @Override
    public boolean rentACar(UUID carID) {
        return false;
    }

    @Override
    public boolean changeCarStatus(UUID carID) {
        return false;
    }

    @Override
    public RequestStatus changeRequestStatus(RequestStatus requestStatus) {
        return null;
    }
}
