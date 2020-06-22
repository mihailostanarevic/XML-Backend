package com.rentacar.rentservice.service;

import com.rentacar.rentservice.dto.feignClient.RequestAdDTO;
import com.rentacar.rentservice.dto.feignClient.RequestDTO;
import com.rentacar.rentservice.dto.request.RequestRequest;
import com.rentacar.rentservice.dto.response.AdResponse;
import com.rentacar.rentservice.dto.response.AgentRequests;
import com.rentacar.rentservice.dto.response.SimpleUserRequests;
import com.rentacar.rentservice.entity.Request;
import com.rentacar.rentservice.util.enums.RequestStatus;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IRequestService {

    void processRequests(List<RequestRequest> requestList);

    Request createRequest(RequestRequest requestRequest);

    Request createBundleRequest(List<RequestRequest> requestList);

    void changeAdAvailability(RequestRequest request);

    Collection<SimpleUserRequests> payRequest(UUID id, UUID requestID);

    Collection<SimpleUserRequests> dropRequest(UUID id, UUID requestID);

    List<SimpleUserRequests> getAllUserRequests(UUID id, RequestStatus reserved);

    Collection<AgentRequests> getAllAgentRequests(UUID userId, RequestStatus carRequestStatus);

    Collection<AgentRequests> approveRequest(UUID agentId, UUID reqID);

    RequestStatus changeRequestStatus(RequestStatus requestStatus);

    List<RequestDTO> getRequestsByStatus(RequestStatus status);

    List<RequestDTO> getAllPaidRequestsByCustomer(UUID id);

    List<RequestAdDTO> getAllRequestAdsByRequest(UUID id);
}
