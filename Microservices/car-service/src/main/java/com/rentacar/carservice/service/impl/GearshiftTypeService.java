package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateGearshiftTypeRequest;
import com.rentacar.carservice.dto.request.UpdateGearshiftTypeRequest;
import com.rentacar.carservice.dto.response.GearshiftTypeResponse;
import com.rentacar.carservice.repository.IGearshiftTypeRepository;
import com.rentacar.carservice.service.IGearshiftTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GearshiftTypeService implements IGearshiftTypeService {

    private final IGearshiftTypeRepository _gearshiftTypeRepository;

    public GearshiftTypeService(IGearshiftTypeRepository gearshiftTypeRepository) {
        _gearshiftTypeRepository = gearshiftTypeRepository;
    }

    @Override
    public GearshiftTypeResponse createGearshiftType(CreateGearshiftTypeRequest request) throws Exception {
        return null;
    }

    @Override
    public GearshiftTypeResponse updateGearshiftType(UpdateGearshiftTypeRequest request, UUID id) throws Exception {
        return null;
    }

    @Override
    public void deleteGearshiftType(UUID id) throws Exception {

    }

    @Override
    public GearshiftTypeResponse getGearshiftType(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<GearshiftTypeResponse> getAllGearshiftTypes() throws Exception {
        return null;
    }
}
