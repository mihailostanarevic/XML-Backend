package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.AddAdRequest;
import com.rentacar.carservice.dto.request.UpdateAdRequest;
import com.rentacar.carservice.dto.response.AdResponse;

import java.util.List;
import java.util.UUID;

public interface IAdService {

    AdResponse createAd(AddAdRequest request) throws Exception;

    AdResponse updateAd(UpdateAdRequest request, UUID id) throws Exception;

    void deleteAd(UUID id) throws Exception;

    AdResponse getAd(UUID id) throws Exception;

    List<AdResponse> getAllAds() throws Exception;

    List<AdResponse> getAllAdsByCarModel(UUID id) throws Exception;

    List<AdResponse> getAllAdsByCarBrand(UUID id) throws Exception;

    List<AdResponse> getAllAdsByCarClass(UUID id) throws Exception;

    List<AdResponse> getAllAdsByGearshiftType(UUID id) throws Exception;

    List<AdResponse> getAllAdsByFuelType(UUID id) throws Exception;

    List<AdResponse> getAllAdsByGas() throws Exception;
}
