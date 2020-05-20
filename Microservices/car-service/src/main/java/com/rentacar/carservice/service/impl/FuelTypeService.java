package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateFuelTypeRequest;
import com.rentacar.carservice.dto.request.UpdateFuelTypeRequest;
import com.rentacar.carservice.dto.response.FuelTypeResponse;
import com.rentacar.carservice.repository.IFuelTypeRepository;
import com.rentacar.carservice.service.IFuelTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuelTypeService implements IFuelTypeService {

    private final IFuelTypeRepository _fuelTypeRepository;

    public FuelTypeService(IFuelTypeRepository fuelTypeRepository) {
        _fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public FuelTypeResponse createFuelType(CreateFuelTypeRequest request) throws Exception {
        return null;
    }

    @Override
    public FuelTypeResponse updateFuelType(UpdateFuelTypeRequest request, UUID id) throws Exception {
        return null;
    }

    @Override
    public void deleteFuelType(UUID id) throws Exception {

    }

    @Override
    public FuelTypeResponse getFuelType(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<FuelTypeResponse> getAllFuelTypes() throws Exception {
        return null;
    }
}
