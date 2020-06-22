package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateCarClassRequest;
import com.rentacar.carservice.dto.request.GetCarClassesWithFilter;
import com.rentacar.carservice.dto.request.UpdateCarClassRequest;
import com.rentacar.carservice.dto.response.CarClassResponse;
import com.rentacar.carservice.entity.CarClass;
import com.rentacar.carservice.repository.ICarClassRepository;
import com.rentacar.carservice.service.ICarClassService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarClassService implements ICarClassService {

    private final ICarClassRepository _carClassRepository;

    public CarClassService(ICarClassRepository carClassRepository) {
        _carClassRepository = carClassRepository;
    }

    @Override
    public CarClassResponse createCarClass(CreateCarClassRequest request) throws Exception {
        CarClass carClass = new CarClass();
        carClass.setDeleted(false);
        carClass.setName(request.getName());
        carClass.setDescription(request.getDescription());
        CarClass savedCarClass = _carClassRepository.save(carClass);
        return mapCarClassToCarClassResponse(savedCarClass);
    }

    @Override
    public CarClassResponse updateCarClass(UpdateCarClassRequest request, UUID id) throws Exception {
        CarClass carClass = _carClassRepository.findOneById(id);
        carClass.setName(request.getName());
        carClass.setDescription(request.getDescription());
        CarClass savedCarClass = _carClassRepository.save(carClass);
        return mapCarClassToCarClassResponse(savedCarClass);
    }

    @Override
    public void deleteCarClass(UUID id) throws Exception {
        CarClass carClass = _carClassRepository.findOneById(id);
        carClass.setDeleted(true);
        _carClassRepository.save(carClass);
    }

    @Override
    public CarClassResponse getCarClass(UUID id) throws Exception {
        CarClass carClass = _carClassRepository.findOneById(id);
        return mapCarClassToCarClassResponse(carClass);
    }

    @Override
    public List<CarClassResponse> getAllCarClasses() throws Exception {
        List<CarClass> carClasses = _carClassRepository.findAllByDeleted(false);
        return carClasses.stream()
                .map(carClass -> mapCarClassToCarClassResponse(carClass))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarClassResponse> getAllCarClassesWithFilter(GetCarClassesWithFilter request) {
        List<CarClass> allCarClasses = _carClassRepository.findAllByDeleted(false);
        return allCarClasses
                .stream()
                .filter(carClass -> {
                    if(request.getClassName() != null) {
                        return carClass.getName().toLowerCase().contains(request.getClassName().toLowerCase());
                    } else {
                        return true;
                    }
                })
                .map(cc -> mapCarClassToCarClassResponse(cc))
                .collect(Collectors.toList());
    }

    private CarClassResponse mapCarClassToCarClassResponse(CarClass carClass) {
        CarClassResponse response = new CarClassResponse();
        response.setId(carClass.getId());
        response.setDescription(carClass.getDescription());
        response.setName(carClass.getName());
        return response;
    }
}
