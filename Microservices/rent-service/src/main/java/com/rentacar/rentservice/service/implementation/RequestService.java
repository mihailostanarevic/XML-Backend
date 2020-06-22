package com.rentacar.rentservice.service.implementation;

import com.rentacar.rentservice.client.AdClient;
import com.rentacar.rentservice.client.AuthClient;
import com.rentacar.rentservice.dto.client.AdClientResponse;
import com.rentacar.rentservice.dto.client.AgentResponse;
import com.rentacar.rentservice.dto.client.CustomerResponse;
import com.rentacar.rentservice.dto.feignClient.RequestAdDTO;
import com.rentacar.rentservice.dto.feignClient.RequestDTO;
import com.rentacar.rentservice.dto.feignClient.SimpleUserDTO;
import com.rentacar.rentservice.dto.client.UUIDResponse;
import com.rentacar.rentservice.dto.request.RequestRequest;
import com.rentacar.rentservice.dto.response.AdResponse;
import com.rentacar.rentservice.dto.response.AgentRequests;
import com.rentacar.rentservice.dto.response.SimpleUserRequests;
import com.rentacar.rentservice.entity.Request;
import com.rentacar.rentservice.entity.RequestAd;
import com.rentacar.rentservice.repository.IRequestAdRepository;
import com.rentacar.rentservice.repository.IRequestRepository;
import com.rentacar.rentservice.service.IRequestService;
import com.rentacar.rentservice.util.enums.RequestStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@SuppressWarnings("SpellCheckingInspection")
@Service
public class RequestService implements IRequestService {

    private final IRequestRepository _requestRepository;
    private final IRequestAdRepository _requestAdRepository;
    private final AuthClient _authClient;
    private final AdClient _adClient;

    public RequestService(IRequestRepository requestRepository, IRequestAdRepository requestAdRepository, AuthClient authClient, AdClient adClient) {
        _requestRepository = requestRepository;
        _requestAdRepository = requestAdRepository;
        _authClient = authClient;
        _adClient = adClient;
    }

    @Override
    public void processRequests(List<RequestRequest> requestList) {
        List<RequestRequest> processedList = new ArrayList<>();
        for (RequestRequest requestDTO : requestList) {
            if (requestDTO.isBundle() && !processedList.contains(requestDTO)) {
                List<RequestRequest> bundleList = new ArrayList<>();
                for (RequestRequest agentRequest : requestList) {
                    if(agentRequest.isBundle()
                            && requestDTO.getAgentID().equals(agentRequest.getAgentID())
                                && !bundleList.contains(agentRequest)) {
                        bundleList.add(agentRequest);
                        processedList.add(agentRequest);
                    }
                }
                createBundleRequest(bundleList);
            } else if (!requestDTO.isBundle()) {
                createRequest(requestDTO);
            }
        }
    }

    /**
     * Check whether the car is available in that period
     * */
    private boolean checkCarAvailability(UUID adID, RequestRequest requestDTO) {
        List<RequestAd> requestAdList = _requestAdRepository.findAllByAdID(adID);
        for (RequestAd requestAd : requestAdList) {
            boolean startEndDate = requestAd.getReturnDate().isBefore(LocalDate.parse(requestDTO.getPickUpDate()));
            if (!startEndDate) {
                boolean endStartDate = LocalDate.parse(requestDTO.getReturnDate()).isBefore(requestAd.getPickUpDate());
                if (!endStartDate) {
                    if(requestAd.getReturnDate().isEqual(LocalDate.parse(requestDTO.getPickUpDate()))) {
                        if (!requestAd.getReturnTime().isBefore(LocalTime.parse(requestDTO.getPickUpTime()))) {
                            return false;
                        }
                    }
                    else if(requestAd.getPickUpDate().isEqual(LocalDate.parse(requestDTO.getReturnDate()))) {
                        if (!requestAd.getPickUpTime().isAfter(LocalTime.parse(requestDTO.getReturnTime()))) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public Request createRequest(RequestRequest requestDTO) {
        Request request = new Request();
        UUID simpleUser = null;
        if(requestDTO.getCustomerID() != null) {
            simpleUser = requestDTO.getCustomerID();
        } else {
            UUIDResponse uuidResponse = _authClient.getIDByUsername(requestDTO.getCustomerUsername());
            simpleUser = uuidResponse.getId();
        }
        request.setCustomerID(simpleUser);
        request.setStatus(RequestStatus.PENDING);
        request.setPickUpAddress(requestDTO.getPickUpAddress());
        request.setDeleted(false);
        List<RequestRequest> requestDTOList = new ArrayList<>();
        requestDTOList.add(requestDTO);
        _requestRepository.save(request);
        createRequestAd(request, requestDTOList);
        _authClient.addUserRole(simpleUser, "ROLE_REQUEST");

        TimerTask taskPending = new TimerTask() {
            public void run() {
                System.out.println("Request performed on: " + LocalTime.now() + ", " +
                        "Request id: " + Thread.currentThread().getName());
                if(request.getStatus().equals(RequestStatus.PENDING)) {
                    request.setStatus(RequestStatus.CANCELED);
                    _requestRepository.save(request);
                }
            }
        };
        Timer timer = new Timer(request.getId().toString());
        long delay = (24 * 60 * 60 * 1000);
        System.out.println("Request received at: " + LocalTime.now());
        timer.schedule(taskPending, delay);

        return request;
    }

    @Override
    public Request createBundleRequest(List<RequestRequest> requestList) {
        Request request = new Request();
        request.setCustomerID(requestList.get(0).getCustomerID());
        request.setStatus(RequestStatus.PENDING);
        request.setPickUpAddress(requestList.get(0).getPickUpAddress());
        request.setDeleted(false);
        _requestRepository.save(request);
        createRequestAd(request, requestList);
        TimerTask taskPending = new TimerTask() {
            public void run() {
                System.out.println("Bundle request performed on: " + LocalTime.now() + ", " +
                        "Request id: " + Thread.currentThread().getName());
                if(request.getStatus().equals(RequestStatus.PENDING)) {
                    request.setStatus(RequestStatus.CANCELED);
                    _requestRepository.save(request);
                }
            }
        };
        Timer timer = new Timer(request.getId().toString());
        long delay = (24 * 60 * 60 * 1000);
        System.out.println("Bundle received at: " + LocalTime.now());
        timer.schedule(taskPending, delay);
        return request;
    }

    private void createRequestAd(Request request, List<RequestRequest> requestDTOList) {
        for (RequestRequest requestDTO : requestDTOList) {
            RequestAd requestAd = new RequestAd();
            requestAd.setPickUpDate(LocalDate.parse(requestDTO.getPickUpDate()));
            requestAd.setPickUpTime(LocalTime.parse(requestDTO.getPickUpTime()));
            requestAd.setReturnDate(LocalDate.parse(requestDTO.getReturnDate()));
            requestAd.setReturnTime(LocalTime.parse(requestDTO.getReturnTime()));
            requestAd.setAdID(requestDTO.getAdID());
            requestAd.setRequest(request);
            _requestAdRepository.save(requestAd);
        }
    }

    @Override
    public void changeAdAvailability(RequestRequest request) {
        for (RequestAd requestAd : _requestAdRepository.findAllByAdID(request.getAdID())) {
            if(!checkCarAvailability(requestAd.getAdID(), request)) {
                requestAd.getRequest().setDeleted(true);
            }
        }
        createRequest(request);
    }

    @Override
    public Collection<SimpleUserRequests> payRequest(UUID userId, UUID resID) {
        Request request = _requestRepository.findOneById(resID);
        if(request.getStatus().equals(RequestStatus.RESERVED)) {
            request.setStatus(RequestStatus.PAID);
            _requestRepository.save(request);
        }

        changeStatusOfRequests(request, RequestStatus.CHECKED, RequestStatus.CANCELED);
        return getAllUserRequests(userId, RequestStatus.RESERVED);
    }

    @Override
    public Collection<SimpleUserRequests> dropRequest(UUID userId, UUID resID) {
        Request request = _requestRepository.findOneById(resID);
        RequestStatus retStatus = request.getStatus();
        if(!request.getStatus().equals(RequestStatus.PAID)) {
            request.setStatus(RequestStatus.DROPPED);
            _requestRepository.save(request);
        }
        return getAllUserRequests(userId, retStatus);
    }

    public void changeStatusOfRequests(Request baseRequest, RequestStatus wakeUpStatus, RequestStatus finalStatus) {
        for (RequestAd requestAd : _requestAdRepository.findAllByRequest(baseRequest)) {
            for (RequestAd requestAdAll : _requestAdRepository.findAll()) {
                if (requestAdAll.getRequest().getStatus().equals(wakeUpStatus)
                        && checkRequestMatching(requestAd, requestAdAll)) {
                    requestAdAll.getRequest().setStatus(finalStatus);
                    _requestRepository.save(requestAdAll.getRequest());
                }
            }
        }
    }

    public boolean checkRequestMatching(RequestAd requestFirst, RequestAd requestSecond) {
        if(requestFirst.getAdID().equals(requestSecond.getAdID())) {
            if (requestFirst.getReturnDate().isBefore(requestSecond.getPickUpDate())) {
                return false;
            } else {
                if (requestFirst.getPickUpDate().isAfter(requestSecond.getReturnDate())) {
                    return false;
                } else {
                    if (requestFirst.getReturnDate().isEqual(requestSecond.getPickUpDate())) {
                        return requestFirst.getReturnTime().isAfter(requestSecond.getPickUpTime());
                    } else if (requestSecond.getReturnDate().isEqual(requestFirst.getPickUpDate())) {
                        return requestFirst.getPickUpTime().isBefore(requestSecond.getReturnTime());
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<SimpleUserRequests> getAllUserRequests(UUID id, RequestStatus requestStatus) {
        List<Request> requestList = new ArrayList<>();
        for (Request request : _requestRepository.findAll()) {
            if (request.getCustomerID().equals(id) && request.getStatus().equals(requestStatus)) {
                if (!requestList.contains(request)) {
                    requestList.add(request);
                }
            }
        }
        return mapToSimpleUserRequest(requestList);
    }

    @Override
    public Collection<AgentRequests> getAllAgentRequests(UUID userId, RequestStatus carRequestStatus) {
        List<Request> requestList = new ArrayList<>();
        for (RequestAd requestAd : _requestAdRepository.findAll()) {
            AgentResponse agentID = _adClient.getAgentIDByAdID(requestAd.getAdID());
            if(agentID.getId().equals(userId) && requestAd.getRequest().getStatus().equals(carRequestStatus)) {
                if(!requestList.contains(requestAd.getRequest())) {
                    requestList.add(requestAd.getRequest());
                }
            }
        }
        return mapToAgentRequest(requestList);
    }

    @Override
    public Collection<AgentRequests> approveRequest(UUID agentId, UUID requestID) {
        Request request = _requestRepository.findOneById(requestID);
        request.setStatus(RequestStatus.RESERVED);
        _requestRepository.save(request);

        changeStatusOfRequests(request, RequestStatus.PENDING, RequestStatus.CHECKED);

        TimerTask taskPaid = new TimerTask() {
            public void run() {
                System.out.println("Approved request performed on: " + LocalTime.now() + ", " +
                        "Request id: " + Thread.currentThread().getName());
                if(!request.getStatus().equals(RequestStatus.PAID)) {
                    request.setStatus(RequestStatus.CANCELED);
                    _requestRepository.save(request);

                    changeStatusOfRequests(request, RequestStatus.CHECKED, RequestStatus.PENDING);
                }
            }
        };
        Timer timer = new Timer(request.getId().toString());
        long delay = (12 * 60 * 60 * 1000);
        System.out.println("Approved request received at: " + LocalTime.now());
        timer.schedule(taskPaid, delay);
        return getAllAgentRequests(agentId, RequestStatus.PENDING);
    }

    @Override
    public RequestStatus changeRequestStatus(RequestStatus requestStatus) {
        return null;
    }

    private List<SimpleUserRequests> mapToSimpleUserRequest(List<Request> requestList) {
        List<SimpleUserRequests> simpleUserRequestList = new ArrayList<>();
        for (Request request : requestList) {
            SimpleUserRequests simpleUserRequests = new SimpleUserRequests();
            // Feign Client getAgentName by adID (izbaciti ovo sa fronta, jer mnogo vremena oduzima, [ugnjezdeni pozivi])
            String[] pickUpAddress = request.getPickUpAddress().split(",");
            simpleUserRequests.setPickUpAddress(pickUpAddress[1].trim() + ", " + pickUpAddress[2].trim() + " " + pickUpAddress[3].trim());
            simpleUserRequests.setReceptionDate(request.getReceptionDate().toString());
            simpleUserRequests.setId(request.getId());
            simpleUserRequests.setRequestStatus(request.getStatus().toString());
            String ads = "";
            String description = "";
            List<RequestAd> requestAdList = _requestAdRepository.findAllByRequest(request);
            for (RequestAd requestAd : requestAdList) {
                AdClientResponse adClientResponse = _adClient.getAdByID(requestAd.getAdID());
                simpleUserRequests.setAgent(adClientResponse.getAdResponse().getAgentName());
                ads += adClientResponse.getAdResponse().getName() + ", ";
                description += "Ad: " + ads + " in period: " + requestAd.getPickUpDate() + " at " +
                        requestAd.getPickUpTime() + " to " + requestAd.getReturnDate() + " at " + requestAd.getReturnTime() + ", ";
            }
            if(requestAdList.size() > 1) {
                ads = ads.substring(0, ads.length() -2);
            }
            description = description.substring(0, description.length() - 2);
            simpleUserRequests.setDescription(description);
            simpleUserRequests.setAd(ads);
            simpleUserRequestList.add(simpleUserRequests);
        }

        return simpleUserRequestList;
    }

    private Collection<AgentRequests> mapToAgentRequest(List<Request> requestList) {
        List<AgentRequests> agentRequestList = new ArrayList<>();
        for (Request request : requestList) {
            AgentRequests agentRequest = new AgentRequests();
            CustomerResponse customerResponse = _authClient.getSimpleUserByID(request.getCustomerID());
            agentRequest.setCustomer(customerResponse.getFirstName() + " " + customerResponse.getLastName());
            String[] pickUpAddress = request.getPickUpAddress().split(",");
            agentRequest.setPickUpAddress(pickUpAddress[1].trim() + ", " + pickUpAddress[2].trim() + " " + pickUpAddress[3].trim());
            agentRequest.setReceptionDate(request.getReceptionDate().toString());
            agentRequest.setId(request.getId());
            agentRequest.setRequestStatus(request.getStatus().toString());
            String ads = "";
            String description = "";
            List<RequestAd> requestAdList = _requestAdRepository.findAllByRequest(request);
            for (RequestAd requestAd : requestAdList) {
                AdClientResponse adClientResponse = _adClient.getAdByID(requestAd.getAdID());
                ads += adClientResponse.getAdResponse().getName() + ", ";
                description += "Ad: " + ads.substring(0, ads.length()-1) + " in period: " + requestAd.getPickUpDate() + " at " +
                        requestAd.getPickUpTime() + " to " + requestAd.getReturnDate() + " at " + requestAd.getReturnTime() + ", ";
            }
            if(requestAdList.size() > 1) {
                ads = ads.substring(0, ads.length() -2);
            }
            description = description.substring(0, description.length() - 2);
            ads = ads.substring(0, ads.length() -1);
            agentRequest.setDescription(description);
            agentRequest.setAd(ads);
            agentRequestList.add(agentRequest);
        }

        return agentRequestList;
    }

    public List<RequestDTO> getRequestsByStatus(RequestStatus status){
        List<RequestDTO> retVal = new ArrayList<>();

        List<Request> requests = _requestRepository.findAllByStatus(status);
        for(Request request : requests){
            RequestDTO dto = new RequestDTO();
            dto.setId(request.getId());
            dto.setStatus(request.getStatus());
            dto.setDeleted(request.isDeleted());
            dto.setReceptionDate(request.getReceptionDate());
            SimpleUserDTO simpleUserDTO = _authClient.getSimpleUser(request.getCustomerID());
            dto.setCustomer(simpleUserDTO);
            dto.setPickUpAddress(request.getPickUpAddress());
            List<RequestAdDTO> list = new ArrayList<>();
            for(RequestAd rqAd : request.getRequestAds()){
                System.out.println(rqAd.getRequest().getId());
                System.out.println(rqAd.getAdID());
                RequestAdDTO rqAdDTO = new RequestAdDTO();
                rqAdDTO.setId(rqAd.getId());
                rqAdDTO.setRequest(request.getId());
                rqAdDTO.setAd_id(rqAd.getAdID());
                rqAdDTO.setPickUpTime(rqAd.getPickUpTime());
                rqAdDTO.setPickUpDate(rqAd.getPickUpDate());
                rqAdDTO.setReturnTime(rqAd.getReturnTime());
                rqAdDTO.setReturnDate(rqAd.getReturnDate());
                list.add(rqAdDTO);
            }
            dto.setRequestAds(list);
            retVal.add(dto);
        }

        return retVal;
    }

    @Override
    public List<RequestDTO> getAllPaidRequestsByCustomer(UUID id) {
        List<Request> requests = _requestRepository.findAllByCustomerIDAndStatus(id, RequestStatus.PAID);
        List<RequestDTO> requestDTOS = new ArrayList<>();
        for(Request request: requests){
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setStatus(RequestStatus.PAID);
            requestDTO.setDeleted(false);
            requestDTO.setId(request.getId());
            requestDTO.setPickUpAddress(request.getPickUpAddress());
            requestDTO.setReceptionDate(request.getReceptionDate());
            SimpleUserDTO simpleUser = _authClient.getSimpleUser(request.getCustomerID());
            requestDTO.setCustomer(simpleUser);
            requestDTOS.add(requestDTO);
        }
        return requestDTOS;
    }

    @Override
    public List<RequestAdDTO> getAllRequestAdsByRequest(UUID id) {
        List<RequestAd> requestAds = _requestAdRepository.findAllByRequest_Id(id);
        List<RequestAdDTO> requestAdDTOS = new ArrayList<>();
        for (RequestAd requestAd: requestAds){
            RequestAdDTO requestAdDTO = new RequestAdDTO();
            requestAdDTO.setId(requestAd.getId());
            requestAdDTO.setAd_id(requestAd.getAdID());
            requestAdDTO.setPickUpDate(requestAd.getPickUpDate());
            requestAdDTO.setPickUpTime(requestAd.getPickUpTime());
            requestAdDTO.setReturnDate(requestAd.getReturnDate());
            requestAdDTO.setReturnTime(requestAd.getReturnTime());
            requestAdDTO.setRequest(requestAd.getRequest().getId());
            requestAdDTOS.add(requestAdDTO);
        }
        return requestAdDTOS;
    }
}
