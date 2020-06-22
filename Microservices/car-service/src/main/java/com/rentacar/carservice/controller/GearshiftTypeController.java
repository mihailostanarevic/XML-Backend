package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.CreateGearshiftTypeRequest;
import com.rentacar.carservice.dto.request.GetGearshiftTypesWithFilterRequest;
import com.rentacar.carservice.dto.request.UpdateGearshiftTypeRequest;
import com.rentacar.carservice.dto.response.GearshiftTypeResponse;
import com.rentacar.carservice.service.IGearshiftTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/gearshift-types")
public class GearshiftTypeController {

    private final IGearshiftTypeService _gearshiftTypeService;

    public GearshiftTypeController(IGearshiftTypeService gearshiftTypeService) {
        _gearshiftTypeService = gearshiftTypeService;
    }

    @PostMapping
    public GearshiftTypeResponse createGearshiftType(@RequestBody CreateGearshiftTypeRequest request) throws Exception{
        return _gearshiftTypeService.createGearshiftType(request);
    }

    @PutMapping("/{id}/gearshift-type")
    public GearshiftTypeResponse updateGearshiftType(@RequestBody UpdateGearshiftTypeRequest request, @PathVariable UUID id) throws Exception{
        return _gearshiftTypeService.updateGearshiftType(request, id);
    }

    @DeleteMapping("/{id}/gearshift-type")
    public void deleteGearshiftType(@PathVariable UUID id) throws Exception{
        _gearshiftTypeService.deleteGearshiftType(id);
    }

    @GetMapping("/{id}/gearshift-type")
    public GearshiftTypeResponse getGearshiftType(@PathVariable UUID id) throws Exception{
        return _gearshiftTypeService.getGearshiftType(id);
    }

    @GetMapping
    public List<GearshiftTypeResponse> getAllGearshiftTypes() throws Exception{
        return _gearshiftTypeService.getAllGearshiftTypes();
    }

    @GetMapping("/with-filter")
    public List<GearshiftTypeResponse> getAllGearshiftTypesWithFilter(GetGearshiftTypesWithFilterRequest request) throws Exception{
        return _gearshiftTypeService.getAllGearshiftTypesWithFilter(request);
    }
}
