package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.CreateFuelTypeRequest;
import com.rentacar.carservice.dto.request.GetFuelTypesWithFilterRequest;
import com.rentacar.carservice.dto.request.UpdateFuelTypeRequest;
import com.rentacar.carservice.dto.response.FuelTypeResponse;
import com.rentacar.carservice.service.IFuelTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fuel-types")
public class FuelTypeController {

    private final IFuelTypeService _fuelTypeService;

    public FuelTypeController(IFuelTypeService fuelTypeService) {
        _fuelTypeService = fuelTypeService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CRUD_FUEL_TYPE')")
    public FuelTypeResponse createFuelType(@RequestBody CreateFuelTypeRequest request) throws Exception{
        return _fuelTypeService.createFuelType(request);
    }

    @PutMapping("/{id}/fuel-type")
    @PreAuthorize("hasAuthority('CRUD_FUEL_TYPE')")
    public FuelTypeResponse updateFuelType(@RequestBody UpdateFuelTypeRequest request, @PathVariable UUID id) throws Exception{
        return _fuelTypeService.updateFuelType(request, id);
    }

    @DeleteMapping("/{id}/fuel-type")
    @PreAuthorize("hasAuthority('CRUD_FUEL_TYPE')")
    public void deleteFuelType(@PathVariable UUID id) throws Exception{
        _fuelTypeService.deleteFuelType(id);
    }

    @GetMapping("/{id}/fuel-type")
    @PreAuthorize("hasAuthority('VIEW_AD')")
    public FuelTypeResponse getFuelType(@PathVariable UUID id) throws Exception{
        return _fuelTypeService.getFuelType(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_AD')")
    public List<FuelTypeResponse> getAllFuelTypes() throws Exception{
        return _fuelTypeService.getAllFuelTypes();
    }

    @GetMapping("/with-filter")
    @PreAuthorize("hasAuthority('VIEW_AD')")
    public List<FuelTypeResponse> getAllFuelTypesWithFilter(GetFuelTypesWithFilterRequest request) throws Exception{
        return _fuelTypeService.getAllFuelTypesWithFilter(request);
    }
}
