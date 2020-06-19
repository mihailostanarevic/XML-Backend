package com.rentacar.searchservice.services.implementation;

import com.rentacar.searchservice.clients.RentClient;
import com.rentacar.searchservice.dto.response.RequestResponse;
import com.rentacar.searchservice.dto.response.SearchResultResponse;
import com.rentacar.searchservice.entity.Ad;
import com.rentacar.searchservice.repository.IAdRepository;
import com.rentacar.searchservice.services.ISearchService;
import com.rentacar.searchservice.util.enums.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SearchService implements ISearchService {

    @Autowired
    private IAdRepository _adRepository;

    @Autowired
    private RentClient _rentClient;

    @Override
    public List<SearchResultResponse> searchAds(String city, String from, String to) {
        List<SearchResultResponse> retVal = new ArrayList<SearchResultResponse>();

        String paramTimeFrom = from.split(" ")[0];
        String paramDateFrom = from.split(" ")[1];
        String paramTimeTo = to.split(" ")[0];
        String paramDateTo = to.split(" ")[1];

        LocalTime timeFrom = LocalTime.parse(paramTimeFrom);
        LocalDate dateFrom = LocalDate.parse(paramDateFrom);
        LocalTime timeTo = LocalTime.parse(paramTimeTo);
        LocalDate dateTo = LocalDate.parse(paramDateTo);

        List<Ad> allAds = _adRepository.findAllByDeleted(false);
        if(!city.equals(""))
            allAds = removeIncorrectCityAd(allAds, city);

        List<RequestResponse> allRequests = _rentClient.getRequestByStatus(RequestStatus.APPROVED);


        return retVal;
    }

    public List<Ad> removeIncorrectCityAd(List<Ad> allAds, String city){
        return allAds
                .stream()
                .filter(ad -> {
                    if(city != null) {
                        return ad.getAddress().getCity().toUpperCase().equals(city.toUpperCase());
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Ad> ignoreNonRelevantAds(List<Ad> allAds, List<RequestResponse> allRequests, LocalDate dateFrom, LocalTime timeFrom, LocalDate dateTo, LocalTime timeTo, String city) {
        List<Ad> retVal = new ArrayList<>();
        for (Ad ad : allAds) {
            retVal.add(ad);
        }

        List<UUID> ids = new ArrayList<>();
        for (RequestResponse request : allRequests) {
            for (RequestAd rqAd : request.getRequestAds()) {
                for (Address address : rqAd.getAd().getAgent().getAddress()) {
                    if (address.getCity().toUpperCase().equals(city.toUpperCase())) {
                        if (!isCompletelyBefore(rqAd, dateFrom, timeFrom) && !isCompletelyAfter(rqAd, dateTo, timeTo)) {
                            if (!ids.contains(rqAd.getAd().getId())) {
                                ids.add(rqAd.getAd().getId());
                            }
                        }
                    }
                }
            }
        }


        for (Ad ad : allAds) {
            for (UUID id : ids) {
                if (ad.getId().equals(id)) {
                    retVal.remove(ad);
                }
            }
        }

        return retVal;
    }

    private List<RequestAd> passableRequests(List<Request> allRequests, LocalDate dateFrom, LocalTime timeFrom, LocalDate dateTo, LocalTime timeTo, String city) {
        List<RequestAd> retVal = new ArrayList<>();
        for(Request request : allRequests){
            for(RequestAd rqAd : request.getRequestAds()) {
                for (Address address : rqAd.getAd().getAgent().getAddress()) {
                    if (address.getCity().toUpperCase().equals(city.toUpperCase())) {
                        if(isCompletelyBefore(rqAd, dateFrom, timeFrom) || isCompletelyAfter(rqAd, dateTo, timeTo)){
                            retVal.add(rqAd);
                        }
                    }
                }
            }
        }

        return retVal;
    }

    public boolean isCompletelyBefore(RequestAd rqAd, LocalDate dateFrom, LocalTime timeFrom){
        //System.out.println(rqAd.getReturnDate() + " " + dateFrom);
        if(rqAd.getReturnDate().isBefore(dateFrom)){
            //System.out.println("usao sam za " + rqAd.getReturnDate() + " datum vracanja, " + dateFrom + "datum iz searcha");
            return true;
        }else {
            if(rqAd.getReturnDate().isEqual(dateFrom) && rqAd.getReturnTime().isBefore(timeFrom)){
                return true;
            }
        }
        return false;
    }

    public boolean isCompletelyAfter(RequestAd rqAd, LocalDate dateTo, LocalTime timeTo){
        //System.out.println(rqAd.getPickUpDate() + " " + dateTo);
        if(rqAd.getPickUpDate().isAfter(dateTo)){
            //System.out.println("usao sam za " + rqAd.getPickUpDate() + " datum vracanja, " + dateTo + "datum iz searcha 213213");
            return true;
        }else {
            if(rqAd.getPickUpDate().isEqual(dateTo) && rqAd.getReturnTime().isAfter(timeTo)){
                return true;
            }
        }
        return false;
    }
}
