package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.AddCarAccessoriesRequest;
import com.rentacar.carservice.dto.request.AddKilometersRequest;
import com.rentacar.carservice.dto.request.CreateCarRequest;
import com.rentacar.carservice.dto.request.UpdateCarRequest;
import com.rentacar.carservice.dto.response.CarAccessoryResponse;
import com.rentacar.carservice.dto.response.CarResponse;
import com.rentacar.carservice.dto.soap.CreateCarSOAP;

import java.util.List;
import java.util.UUID;

public interface ICarService {

    CarResponse createCar(CreateCarRequest request) throws Exception;

    CarResponse updateCar(UpdateCarRequest request, UUID id) throws Exception;

    void deleteCar(UUID id) throws Exception;

    CarResponse getCar(UUID id) throws Exception;

    List<CarResponse> getAllCars() throws Exception;

    void addKilometers(AddKilometersRequest request, UUID id) throws Exception;

    void addCarAccessories(AddCarAccessoriesRequest request) throws Exception; //u request dva id-a

    List<CarAccessoryResponse> getCarAccessories(UUID id);

    void createCarViaSOAP(CreateCarSOAP request);
}
