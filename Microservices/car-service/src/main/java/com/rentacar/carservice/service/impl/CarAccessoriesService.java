package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateCarAccessoriesRequest;
import com.rentacar.carservice.dto.request.UpdateCarAccessoriesRequest;
import com.rentacar.carservice.dto.response.CarAccessoriesResponse;
import com.rentacar.carservice.repository.ICarAccessoriesRepository;
import com.rentacar.carservice.service.ICarAccessoriesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarAccessoriesService implements ICarAccessoriesService {

    private final ICarAccessoriesRepository _carAccessoriesRepository;

    public CarAccessoriesService(ICarAccessoriesRepository carAccessoriesRepository) {
        _carAccessoriesRepository = carAccessoriesRepository;
    }

    @Override
    public CarAccessoriesResponse createCarAccessories(CreateCarAccessoriesRequest request) throws Exception {
        return null;
    }

    @Override
    public CarAccessoriesResponse updateCarAccessories(UpdateCarAccessoriesRequest request, UUID id) throws Exception {
        return null;
    }

    @Override
    public void deleteCarAccessories(UUID id) throws Exception {

    }

    @Override
    public CarAccessoriesResponse getCarAccessories(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<CarAccessoriesResponse> getAllCarAccessories() throws Exception {
        return null;
    }
}
