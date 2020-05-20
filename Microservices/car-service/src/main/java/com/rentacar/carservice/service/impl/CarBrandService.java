package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateCarBrandRequest;
import com.rentacar.carservice.dto.request.UpdateCarBrandRequest;
import com.rentacar.carservice.dto.response.CarBrandResponse;
import com.rentacar.carservice.repository.ICarBrandRepository;
import com.rentacar.carservice.service.ICarBrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarBrandService implements ICarBrandService {

    private final ICarBrandRepository _carBrandRepository;

    public CarBrandService(ICarBrandRepository carBrandRepository) {
        _carBrandRepository = carBrandRepository;
    }

    @Override
    public CarBrandResponse createCarBrand(CreateCarBrandRequest request) throws Exception {
        return null;
    }

    @Override
    public CarBrandResponse updateCarBrand(UpdateCarBrandRequest request, UUID id) throws Exception {
        return null;
    }

    @Override
    public void deleteCarBrand(UUID id) throws Exception {

    }

    @Override
    public CarBrandResponse getCarBrand(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<CarBrandResponse> getAllCarBrands() throws Exception {
        return null;
    }
}
