package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.CreateFuelTypeRequest;
import com.rentacar.carservice.dto.request.GetFuelTypesWithFilterRequest;
import com.rentacar.carservice.dto.request.UpdateFuelTypeRequest;
import com.rentacar.carservice.dto.response.FuelTypeResponse;

import java.util.List;
import java.util.UUID;

public interface IFuelTypeService {

    FuelTypeResponse createFuelType(CreateFuelTypeRequest request) throws Exception;

    FuelTypeResponse updateFuelType(UpdateFuelTypeRequest request, UUID id) throws Exception;

    void deleteFuelType(UUID id) throws Exception;

    FuelTypeResponse getFuelType(UUID id) throws Exception;

    List<FuelTypeResponse> getAllFuelTypes() throws Exception;

    List<FuelTypeResponse> getAllFuelTypesWithFilter(GetFuelTypesWithFilterRequest request);
}
