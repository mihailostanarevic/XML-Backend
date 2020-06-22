package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.CreateCarAccessoriesRequest;
import com.rentacar.carservice.dto.request.UpdateCarAccessoriesRequest;
import com.rentacar.carservice.dto.response.CarAccessoriesResponse;
import com.rentacar.carservice.service.ICarAccessoriesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/car-accessories")
public class CarAccessoriesController {

    private final ICarAccessoriesService _carAccessoriesService;

    public CarAccessoriesController(ICarAccessoriesService carAccessoriesService) {
        _carAccessoriesService = carAccessoriesService;
    }

    public CarAccessoriesResponse createCarAccessories(@RequestBody CreateCarAccessoriesRequest request) throws Exception{
        return _carAccessoriesService.createCarAccessories(request);
    }

    @PutMapping("/{id}/car-accessories")
    public CarAccessoriesResponse updateCarAccessories(@RequestBody UpdateCarAccessoriesRequest request, @PathVariable UUID id) throws Exception{
        return _carAccessoriesService.updateCarAccessories(request, id);
    }

    @DeleteMapping("/{id}/car-accessories")
    public void deleteCarAccessories(@PathVariable UUID id) throws Exception{
        _carAccessoriesService.deleteCarAccessories(id);
    }

    @GetMapping("/{id}/car-accessories")
    public CarAccessoriesResponse getCarAccessories(@PathVariable UUID id) throws Exception{
        return _carAccessoriesService.getCarAccessories(id);
    }

    @GetMapping
    public List<CarAccessoriesResponse> getAllCarAccessories() throws Exception{
        return _carAccessoriesService.getAllCarAccessories();
    }
}
