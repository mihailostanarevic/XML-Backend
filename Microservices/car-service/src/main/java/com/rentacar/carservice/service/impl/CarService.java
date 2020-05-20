package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.AddCarAccessoriesRequest;
import com.rentacar.carservice.dto.request.CreateCarRequest;
import com.rentacar.carservice.dto.request.UpdateCarRequest;
import com.rentacar.carservice.dto.response.CarResponse;
import com.rentacar.carservice.repository.ICarRepository;
import com.rentacar.carservice.service.ICarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService implements ICarService {

    private final ICarRepository _carRepository;

    public CarService(ICarRepository carRepository) {
        _carRepository = carRepository;
    }

    @Override
    public CarResponse createCar(CreateCarRequest request) throws Exception {
        return null;
    }

    @Override
    public CarResponse updateCar(UpdateCarRequest request, UUID id) throws Exception {
        return null;
    }

    @Override
    public void deleteCar(UUID id) throws Exception {

    }

    @Override
    public CarResponse getCar(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<CarResponse> getAllCars() throws Exception {
        return null;
    }

    @Override
    public void addCarAccessories(AddCarAccessoriesRequest request) throws Exception {

    }
}
