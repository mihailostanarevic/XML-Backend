package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateCarModelRequest;
import com.rentacar.carservice.dto.request.UpdateCarModelRequest;
import com.rentacar.carservice.dto.response.CarModelResponse;
import com.rentacar.carservice.repository.ICarModelRepository;
import com.rentacar.carservice.service.ICarModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarModelService implements ICarModelService {

    private final ICarModelRepository _carModelRepository;

    public CarModelService(ICarModelRepository carModelRepository) {
        _carModelRepository = carModelRepository;
    }

    @Override
    public CarModelResponse createCarModel(CreateCarModelRequest request) throws Exception {
        return null;
    }

    @Override
    public CarModelResponse updateCarModel(UpdateCarModelRequest request, UUID id) throws Exception {
        return null;
    }

    @Override
    public void deleteCarModel(UUID id) throws Exception {

    }

    @Override
    public CarModelResponse getCarModel(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<CarModelResponse> getAllCarModels() throws Exception {
        return null;
    }
}
