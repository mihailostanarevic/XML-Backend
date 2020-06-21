package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.CreateCarAccessoriesRequest;
import com.rentacar.carservice.dto.request.UpdateCarAccessoriesRequest;
import com.rentacar.carservice.dto.response.CarAccessoriesResponse;
import com.rentacar.carservice.dto.response.CarAccessoryResponse;

import java.util.List;
import java.util.UUID;

public interface ICarAccessoriesService {

    CarAccessoriesResponse createCarAccessories(CreateCarAccessoriesRequest request) throws Exception;

    CarAccessoriesResponse updateCarAccessories(UpdateCarAccessoriesRequest request, UUID id) throws Exception;

    void deleteCarAccessories(UUID id) throws Exception;

    CarAccessoriesResponse getCarAccessories(UUID id) throws Exception;

    List<CarAccessoriesResponse> getAllCarAccessories() throws Exception;

    List<CarAccessoryResponse> getAll();
}
