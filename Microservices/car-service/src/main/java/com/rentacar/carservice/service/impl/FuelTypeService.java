package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateFuelTypeRequest;
import com.rentacar.carservice.dto.request.UpdateFuelTypeRequest;
import com.rentacar.carservice.dto.response.FuelTypeResponse;
import com.rentacar.carservice.entity.FuelType;
import com.rentacar.carservice.repository.IFuelTypeRepository;
import com.rentacar.carservice.service.IFuelTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuelTypeService implements IFuelTypeService {

    private final IFuelTypeRepository _fuelTypeRepository;

    public FuelTypeService(IFuelTypeRepository fuelTypeRepository) {
        _fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public FuelTypeResponse createFuelType(CreateFuelTypeRequest request) throws Exception {
        FuelType fuelType = new FuelType();
        fuelType.setDeleted(false);
        fuelType.setGas(request.isGas());
        fuelType.setTankCapacity(request.getTankCapacity());
        fuelType.setType(request.getType());
        FuelType savedFuelType = _fuelTypeRepository.save(fuelType);
        return mapFuelTypeToFuelTypeResponse(savedFuelType);
    }

    @Override
    public FuelTypeResponse updateFuelType(UpdateFuelTypeRequest request, UUID id) throws Exception {
        FuelType fuelType = _fuelTypeRepository.findOneById(id);
        fuelType.setGas(request.isGas());
        fuelType.setTankCapacity(request.getTankCapacity());
        fuelType.setType(request.getType());
        FuelType savedFuelType = _fuelTypeRepository.save(fuelType);
        return mapFuelTypeToFuelTypeResponse(savedFuelType);
    }

    @Override
    public void deleteFuelType(UUID id) throws Exception {
        FuelType fuelType = _fuelTypeRepository.findOneById(id);
        fuelType.setDeleted(true);
        _fuelTypeRepository.save(fuelType);
    }

    @Override
    public FuelTypeResponse getFuelType(UUID id) throws Exception {
        FuelType fuelType = _fuelTypeRepository.findOneById(id);
        return mapFuelTypeToFuelTypeResponse(fuelType);
    }

    @Override
    public List<FuelTypeResponse> getAllFuelTypes() throws Exception {
        return null;
    }

    private FuelTypeResponse mapFuelTypeToFuelTypeResponse(FuelType fuelType) {
        FuelTypeResponse response = new FuelTypeResponse();
        response.setGas(fuelType.isGas());
        response.setId(fuelType.getId());
        response.setTankCapacity(fuelType.getTankCapacity());
        response.setType(fuelType.getType());
        return response;
    }
}
