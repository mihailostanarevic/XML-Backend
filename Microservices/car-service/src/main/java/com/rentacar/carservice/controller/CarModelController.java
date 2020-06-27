package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.CreateCarModelRequest;
import com.rentacar.carservice.dto.request.GetCarModelsFilterRequest;
import com.rentacar.carservice.dto.request.UpdateCarModelRequest;
import com.rentacar.carservice.dto.response.CarModelResponse;
import com.rentacar.carservice.service.ICarModelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/car-models")
public class CarModelController {

    private final ICarModelService _carModelService;

    public CarModelController(ICarModelService carModelService) {
        _carModelService = carModelService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('UPDATE_AD')")
    public CarModelResponse createCarMode(@RequestBody CreateCarModelRequest request) throws Exception{
        return _carModelService.createCarModel(request);
    }

    @PutMapping("/{id}/car-model")
    @PreAuthorize("hasAuthority('CRUD_CAR_MODEL')")
    public CarModelResponse updateCarModel(@RequestBody UpdateCarModelRequest request, @PathVariable UUID id) throws Exception{
        return _carModelService.updateCarModel(request, id);
    }

    @DeleteMapping("/{id}/car-model")
    @PreAuthorize("hasAuthority('CRUD_CAR_MODEL')")
    public void deleteCarModel(@PathVariable UUID id) throws Exception{
        _carModelService.deleteCarModel(id);
    }

    @GetMapping("/{id}/car-model")
    @PreAuthorize("hasAuthority('VIEW_AD')")
    public CarModelResponse getCarModel(@PathVariable UUID id) throws Exception{
        return _carModelService.getCarModel(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_AD')")
    public List<CarModelResponse> getAllCarModels() throws Exception{
        return _carModelService.getAllCarModels();
    }

    @GetMapping("/with-filter")
    @PreAuthorize("hasAuthority('VIEW_AD')")
    public List<CarModelResponse> getAllCarModelsWithFilter(GetCarModelsFilterRequest request) throws Exception{
        return _carModelService.getAllCarModelsWithFilter(request);
    }
}
