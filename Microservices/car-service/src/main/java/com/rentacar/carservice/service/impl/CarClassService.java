package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateCarClassRequest;
import com.rentacar.carservice.dto.request.UpdateCarClassRequest;
import com.rentacar.carservice.dto.response.CarClassResponse;
import com.rentacar.carservice.repository.ICarClassRepository;
import com.rentacar.carservice.service.ICarClassService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarClassService implements ICarClassService {

    private final ICarClassRepository _carClassRepository;

    public CarClassService(ICarClassRepository carClassRepository) {
        _carClassRepository = carClassRepository;
    }

    @Override
    public CarClassResponse createCarClass(CreateCarClassRequest request) throws Exception {
        return null;
    }

    @Override
    public CarClassResponse updateCarClass(UpdateCarClassRequest request, UUID id) throws Exception {
        return null;
    }

    @Override
    public void deleteCarClass(UUID id) throws Exception {

    }

    @Override
    public CarClassResponse getCarClass(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<CarClassResponse> getAllCarClasses() throws Exception {
        return null;
    }
}
