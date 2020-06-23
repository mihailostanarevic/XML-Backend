package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.CreateGearshiftTypeRequest;
import com.rentacar.carservice.dto.request.GetGearshiftTypesWithFilterRequest;
import com.rentacar.carservice.dto.request.UpdateGearshiftTypeRequest;
import com.rentacar.carservice.dto.response.GearshiftTypeResponse;
import com.rentacar.carservice.model.CreateGearshiftTypeRequestDTO;

import java.util.List;
import java.util.UUID;

public interface IGearshiftTypeService {

    GearshiftTypeResponse createGearshiftType(CreateGearshiftTypeRequest request) throws Exception;

    Long createGearshiftTypeViaSOAP(CreateGearshiftTypeRequestDTO request);

    GearshiftTypeResponse updateGearshiftType(UpdateGearshiftTypeRequest request, UUID id) throws Exception;

    void deleteGearshiftType(UUID id) throws Exception;

    GearshiftTypeResponse getGearshiftType(UUID id) throws Exception;

    List<GearshiftTypeResponse> getAllGearshiftTypes() throws Exception;

    List<GearshiftTypeResponse> getAllGearshiftTypesWithFilter(GetGearshiftTypesWithFilterRequest request);
}
