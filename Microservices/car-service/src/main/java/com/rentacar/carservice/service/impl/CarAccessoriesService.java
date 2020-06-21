package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateCarAccessoriesRequest;
import com.rentacar.carservice.dto.request.UpdateCarAccessoriesRequest;
import com.rentacar.carservice.dto.response.CarAccessoriesResponse;
import com.rentacar.carservice.dto.response.CarAccessoryResponse;
import com.rentacar.carservice.entity.CarAccessories;
import com.rentacar.carservice.repository.ICarAccessoriesRepository;
import com.rentacar.carservice.service.ICarAccessoriesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarAccessoriesService implements ICarAccessoriesService {

    private final ICarAccessoriesRepository _carAccessoriesRepository;

    public CarAccessoriesService(ICarAccessoriesRepository carAccessoriesRepository) {
        _carAccessoriesRepository = carAccessoriesRepository;
    }

    @Override
    public CarAccessoriesResponse createCarAccessories(CreateCarAccessoriesRequest request) throws Exception {
        CarAccessories carAccessories = new CarAccessories();
        carAccessories.setDeleted(false);
        carAccessories.setDescription(request.getDescription());
        CarAccessories savedCarAccessories = _carAccessoriesRepository.save(carAccessories);
        return mapCarAccessoriesToCarAccessoriesResponse(savedCarAccessories);
    }

    @Override
    public CarAccessoriesResponse updateCarAccessories(UpdateCarAccessoriesRequest request, UUID id) throws Exception {
        CarAccessories carAccessories = _carAccessoriesRepository.findOneById(id);
        carAccessories.setDescription(request.getDescription());
        CarAccessories savedCarAccessories = _carAccessoriesRepository.save(carAccessories);
        return mapCarAccessoriesToCarAccessoriesResponse(savedCarAccessories);
    }

    @Override
    public void deleteCarAccessories(UUID id) throws Exception {
        CarAccessories carAccessories = _carAccessoriesRepository.findOneById(id);
        carAccessories.setDeleted(true);
        _carAccessoriesRepository.save(carAccessories);
    }

    @Override
    public CarAccessoriesResponse getCarAccessories(UUID id) throws Exception {
        CarAccessories carAccessories = _carAccessoriesRepository.findOneById(id);
        return mapCarAccessoriesToCarAccessoriesResponse(carAccessories);
    }

    @Override
    public List<CarAccessoriesResponse> getAllCarAccessories() throws Exception {
        List<CarAccessories> carAccessories = _carAccessoriesRepository.findAllByDeleted(false);
        return carAccessories.stream()
                .map(carAccessories1 -> mapCarAccessoriesToCarAccessoriesResponse(carAccessories1))
                .collect(Collectors.toList());
    }

    private CarAccessoriesResponse mapCarAccessoriesToCarAccessoriesResponse(CarAccessories carAccessories) {
        CarAccessoriesResponse response = new CarAccessoriesResponse();
        response.setId(carAccessories.getId());
        response.setDescription(carAccessories.getDescription());
        return response;
    }

    @Override
    public List<CarAccessoryResponse> getAll() {
        return mapCarAccessoriesToResponse(_carAccessoriesRepository.findAll()) ;
    }

    public List<CarAccessoryResponse> mapCarAccessoriesToResponse(List<CarAccessories> carAccessories) {
        List<CarAccessoryResponse> retVal = new ArrayList<>();

        for(CarAccessories carAccessory : carAccessories){
            if(!carAccessory.isDeleted()){
                CarAccessoryResponse dto = new CarAccessoryResponse(carAccessory.getId(), null, carAccessory.getDescription());
                retVal.add(dto);
            }
        }

        return retVal;
    }
}
