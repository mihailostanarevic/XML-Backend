package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.CreateCarClassRequest;
import com.rentacar.carservice.dto.request.UpdateCarClassRequest;
import com.rentacar.carservice.dto.response.CarClassResponse;

import java.util.List;
import java.util.UUID;

public interface ICarClassService {

    CarClassResponse createCarClass(CreateCarClassRequest request) throws Exception;

    CarClassResponse updateCarClass(UpdateCarClassRequest request, UUID id) throws Exception;

    void deleteCarClass(UUID id) throws Exception;

    CarClassResponse getCarClass(UUID id) throws Exception;

    List<CarClassResponse> getAllCarClasses() throws Exception;
}
