package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateCarModelRequest;
import com.rentacar.carservice.dto.request.UpdateCarModelRequest;
import com.rentacar.carservice.dto.response.CarModelResponse;
import com.rentacar.carservice.entity.CarModel;
import com.rentacar.carservice.repository.ICarModelRepository;
import com.rentacar.carservice.service.ICarModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarModelService implements ICarModelService {

    private final ICarModelRepository _carModelRepository;

    public CarModelService(ICarModelRepository carModelRepository) {
        _carModelRepository = carModelRepository;
    }

    @Override
    public CarModelResponse createCarModel(CreateCarModelRequest request) throws Exception {
        CarModel carModel = new CarModel();
        carModel.setDeleted(false);
        carModel.setName(request.getName());
        CarModel savedCarModel = _carModelRepository.save(carModel);
        return mapCarModelToCarModelResponse(savedCarModel);
    }

    @Override
    public CarModelResponse updateCarModel(UpdateCarModelRequest request, UUID id) throws Exception {
        CarModel carModel = _carModelRepository.findOneById(id);
        carModel.setName(request.getName());
        CarModel savedCarModel = _carModelRepository.save(carModel);
        return mapCarModelToCarModelResponse(savedCarModel);
    }

    @Override
    public void deleteCarModel(UUID id) throws Exception {
        CarModel carModel = _carModelRepository.findOneById(id);
        carModel.setDeleted(true);
        _carModelRepository.save(carModel);
    }

    @Override
    public CarModelResponse getCarModel(UUID id) throws Exception {
        CarModel carModel = _carModelRepository.findOneById(id);
        return mapCarModelToCarModelResponse(carModel);
    }

    @Override
    public List<CarModelResponse> getAllCarModels() throws Exception {
        List<CarModel> carModels = _carModelRepository.findAllByDeleted(false);
        return carModels.stream()
                .map(carModel -> mapCarModelToCarModelResponse(carModel))
                .collect(Collectors.toList());
    }

    private CarModelResponse mapCarModelToCarModelResponse(CarModel carModel) {
        CarModelResponse response = new CarModelResponse();
        response.setId(carModel.getId());
        response.setName(carModel.getName());
        return response;
    }
}
