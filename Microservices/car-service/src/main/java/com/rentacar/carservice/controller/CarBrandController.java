package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.CreateCarBrandRequest;
import com.rentacar.carservice.dto.request.UpdateCarBrandRequest;
import com.rentacar.carservice.dto.response.CarBrandResponse;
import com.rentacar.carservice.service.ICarBrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/car-brands")
public class CarBrandController {

    private final ICarBrandService _carBrandService;

    public CarBrandController(ICarBrandService carBrandService) {
        _carBrandService = carBrandService;
    }

    @PostMapping
    public CarBrandResponse createCarBrand(@RequestBody CreateCarBrandRequest request) throws Exception{
        return _carBrandService.createCarBrand(request);
    }

    @PutMapping("/{id}/car-brand")
    public CarBrandResponse updateCarBrand(@RequestBody UpdateCarBrandRequest request, @PathVariable UUID id) throws Exception{
        return _carBrandService.updateCarBrand(request, id);
    }

    @DeleteMapping("/{id}/car-brand")
    public void deleteCarBrand(@PathVariable UUID id) throws Exception{
        _carBrandService.deleteCarBrand(id);
    }

    @GetMapping("/{id}/car-brand")
    public CarBrandResponse getCarBrand(@PathVariable UUID id) throws Exception{
        return _carBrandService.getCarBrand(id);
    }

    @GetMapping
    public List<CarBrandResponse> getAllCarBrands() throws Exception{
        return _carBrandService.getAllCarBrands();
    }
}
