package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.AddCarAccessoriesRequest;
import com.rentacar.carservice.dto.request.AddKilometersRequest;
import com.rentacar.carservice.dto.request.CreateCarRequest;
import com.rentacar.carservice.dto.request.UpdateCarRequest;
import com.rentacar.carservice.dto.response.CarAccessoryResponse;
import com.rentacar.carservice.dto.response.CarResponse;
import com.rentacar.carservice.entity.Car;
import com.rentacar.carservice.entity.CarAccessories;
import com.rentacar.carservice.repository.*;
import com.rentacar.carservice.service.ICarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService implements ICarService {

    private final ICarRepository _carRepository;
    private final ICarModelRepository _carModelRepository;
    private final IGearshiftTypeRepository _gearshiftTypeRepository;
    private final IFuelTypeRepository _fuelTypeRepository;
    private final ICarAccessoriesRepository _carAccessoriesRepository;
    private final CarAccessoriesService _carAccessoriesService;
    private final Logger logger = LoggerFactory.getLogger("Ad service app: " + this.getClass());

    public CarService(ICarRepository carRepository, ICarModelRepository carModelRepository, IGearshiftTypeRepository gearshiftTypeRepository, IFuelTypeRepository fuelTypeRepository, ICarAccessoriesRepository carAccessoriesRepository, CarAccessoriesService carAccessoriesService) {
        _carRepository = carRepository;
        _carModelRepository = carModelRepository;
        _gearshiftTypeRepository = gearshiftTypeRepository;
        _fuelTypeRepository = fuelTypeRepository;
        _carAccessoriesRepository = carAccessoriesRepository;
        _carAccessoriesService = carAccessoriesService;
    }

    @Override
    public CarResponse createCar(CreateCarRequest request) throws Exception {
        Car car = new Car();
        car.setDeleted(false);
        car.setKilometersTraveled(request.getKilometersTraveled());
        car.setCarModel(_carModelRepository.findOneById(request.getCarModelId()));
        car.setGearshiftType(_gearshiftTypeRepository.findOneById(request.getGearshiftTypeId()));
        car.setFuelType(_fuelTypeRepository.findOneById(request.getFuelTypeId()));
        Car savedCar = _carRepository.save(car);
        logger.info("A new car has been created with id: " + car.getId());
        return mapCarToCarResponse(savedCar);
    }

    @Override
    public CarResponse updateCar(UpdateCarRequest request, UUID id) throws Exception {
        Car car = _carRepository.findOneById(id);
        car.setKilometersTraveled(request.getKilometersTraveled());
        car.getFuelType().setGas(request.isGas());
        _fuelTypeRepository.save(car.getFuelType());
        Car savedCar = _carRepository.save(car);
        logger.info("Car with id: " + car.getId() + " has been updated");
        return mapCarToCarResponse(savedCar);
    }

    @Override
    public void deleteCar(UUID id) throws Exception {
        Car car = _carRepository.findOneById(id);
        car.setDeleted(true);
        _carRepository.save(car);
        logger.info("Car with id: " + car.getId() + " has been deleted");
    }

    @Override
    public CarResponse getCar(UUID id) throws Exception {
        Car car = _carRepository.findOneById(id);
        return mapCarToCarResponse(car);
    }

    @Override
    public List<CarResponse> getAllCars() throws Exception {
        List<Car> cars = _carRepository.findAllByDeleted(false);
        return cars.stream()
                .map(car -> mapCarToCarResponse(car))
                .collect(Collectors.toList());
    }

    @Override
    public void addKilometers(AddKilometersRequest request, UUID id) throws Exception {
        Car car = _carRepository.findOneById(id);
        String kilometers = car.getKilometersTraveled();
        kilometers += Float.parseFloat(request.getKilometersTraveled());
        car.setKilometersTraveled(kilometers);
        _carRepository.save(car);
    }

    @Override
    public void addCarAccessories(AddCarAccessoriesRequest request) throws Exception {
        Car car = _carRepository.findOneById(request.getCarId());
        CarAccessories carAccessories = _carAccessoriesRepository.findOneById(request.getCarAccessoriesId());
        car.getCarAccessories().add(carAccessories);
        _carRepository.save(car);
        carAccessories.getCars().add(car);
        _carAccessoriesRepository.save(carAccessories);
    }

    @Override
    public List<CarAccessoryResponse> getCarAccessories(UUID id) {
        List<Car> cars = _carRepository.findAll();
        for(Car car : cars){
            if(car.getId().equals(id)){
                return _carAccessoriesService.mapCarAccessoriesToResponse(car.getCarAccessories());
            }
        }
        return new ArrayList<CarAccessoryResponse>();
    }

    private CarResponse mapCarToCarResponse(Car car) {
        CarResponse response = new CarResponse();
        response.setId(car.getId());
        response.setKilometersTraveled(car.getKilometersTraveled());
        response.setCarBrandName(car.getCarModel().getCarBrand().getName());
        response.setCarBrandCountry(car.getCarModel().getCarBrand().getCountry());
        response.setCarClassName(car.getCarModel().getCarClass().getName());
        response.setCarClassDescription(car.getCarModel().getCarClass().getDescription());
        response.setCarModelName(car.getCarModel().getName());
        response.setGearshiftTypeType(car.getGearshiftType().getType());
        response.setGearshiftTypeNumberOfGears(car.getGearshiftType().getNumberOfGears());
        response.setFuelTypeType(car.getFuelType().getType());
        response.setFuelTypeTankCapacity(car.getFuelType().getTankCapacity());
        response.setFuelTypeGas(car.getFuelType().isGas());
        return response;
    }
}
