package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.CreateCarModelRequest;
import com.rentacar.carservice.dto.request.GetCarModelsFilterRequest;
import com.rentacar.carservice.dto.request.UpdateCarModelRequest;
import com.rentacar.carservice.dto.response.CarModelResponse;
import com.rentacar.carservice.dto.soap.CreateCarModelSOAP;

import java.util.List;
import java.util.UUID;

public interface ICarModelService {

    CarModelResponse createCarModel(CreateCarModelRequest request) throws Exception;

    CarModelResponse updateCarModel(UpdateCarModelRequest request, UUID id) throws Exception;

    void deleteCarModel(UUID id) throws Exception;

    CarModelResponse getCarModel(UUID id) throws Exception;

    List<CarModelResponse> getAllCarModels() throws Exception;

    List<CarModelResponse> getAllCarModelsWithFilter(GetCarModelsFilterRequest request);

    List<CarModelResponse> getCarModelsByBrand(UUID id);

    void createCarModelViaSOAP(CreateCarModelSOAP request);
}
