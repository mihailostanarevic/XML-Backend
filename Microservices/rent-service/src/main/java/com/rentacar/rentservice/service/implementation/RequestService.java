package com.rentacar.rentservice.service.implementation;

import com.rentacar.rentservice.dto.RequestDTO;
import com.rentacar.rentservice.entity.Address;
import com.rentacar.rentservice.entity.Request;
import com.rentacar.rentservice.repository.IRequestRepository;
import com.rentacar.rentservice.service.IRequestService;
import com.rentacar.rentservice.util.enums.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@Service
public class RequestService implements IRequestService {

    @Autowired
    IRequestRepository _requestRepository;

    @Override
    public void processRequests(List<RequestDTO> requestList) {
        List<RequestDTO> processedList = new ArrayList<>();
        for (RequestDTO requestDTO : requestList) {
            if(requestDTO.isBundle() && !processedList.contains(requestDTO)) {
                List<RequestDTO> bundleList = new ArrayList<>();
                for (RequestDTO agentRequest : requestList) {
                    if(agentRequest.getAgentID().equals(requestDTO.getAgentID()) && !bundleList.contains(agentRequest)) {
                        bundleList.add(agentRequest);
                        processedList.add(agentRequest);
                    }
                }
                createBundleRequest(bundleList);
            } else if(!requestDTO.isBundle()) {
                createRequest(requestDTO);
            }
        }
    }

    @Override
    public Request createRequest(RequestDTO requestDTO) {
        Request request = new Request();
        request.setCarID(requestDTO.getCarID().toString());
        createRequestWithoutCarID(request, requestDTO);
        _requestRepository.save(request);
        return request;
    }

    @Override
    public Request createBundleRequest(List<RequestDTO> requestList) {
        Request request = new Request();
        StringBuilder sb = new StringBuilder();
        String carIds = "";
        for (RequestDTO requestDTO : requestList) {
            carIds = sb.append(requestDTO.getCarID().toString() + ",").toString();
        }
        carIds = carIds.substring(0, carIds.length() - 1);      // obrisi zarez na kraju
        request.setCarID(carIds);
        createRequestWithoutCarID(request, requestList.get(0));
        _requestRepository.save(request);
        return request;
    }

    private Request createRequestWithoutCarID(Request request, RequestDTO requestDTO) {
        request.setCustomerID(requestDTO.getCustomerID());
        request.setAgentID(requestDTO.getAgentID());
        request.setStatus(RequestStatus.PENDING);
        request.setReceptionDate(LocalDate.parse(requestDTO.getReceptionDate()));
        request.setPickUpDate(LocalDate.parse(requestDTO.getPickUpDate()));
        request.setPickUpTime(LocalTime.parse(requestDTO.getPickUpTime()));
        request.setReturnDate(LocalDate.parse(requestDTO.getReturnDate()));
        request.setReturnTime(LocalTime.parse(requestDTO.getReturnTime()));
        Address address = new Address();
        address.setStreet(requestDTO.getPickUpAddress().getStreet());
        address.setNumber(requestDTO.getPickUpAddress().getNumber());
        address.setCity(requestDTO.getPickUpAddress().getCity());
        address.setCountry(requestDTO.getPickUpAddress().getCountry());
        request.setPickUpAddress(address);
        request.setDeleted(false);
        return request;
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
