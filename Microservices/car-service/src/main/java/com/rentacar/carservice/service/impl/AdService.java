package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.AddAdRequest;
import com.rentacar.carservice.dto.request.UpdateAdRequest;
import com.rentacar.carservice.dto.response.AdResponse;
import com.rentacar.carservice.repository.IAdRepository;
import com.rentacar.carservice.service.IAdService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdService implements IAdService {

    private final IAdRepository _adRepository;

    public AdService(IAdRepository adRepository) {
        _adRepository = adRepository;
    }

    @Override
    public AdResponse createAd(AddAdRequest request) throws Exception {
        return null;
    }

    @Override
    public AdResponse updateAd(UpdateAdRequest request, UUID id) throws Exception {
        return null;
    }

    @Override
    public void deleteAd(UUID id) throws Exception {

    }

    @Override
    public AdResponse getAd(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAds() throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByCarModel(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByCarBrand(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByCarClass(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByGearshiftType(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByFuelType(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByGas() throws Exception {
        return null;
    }
}
