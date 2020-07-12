package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateFuelTypeRequest;
import com.rentacar.carservice.dto.request.GetFuelTypesWithFilterRequest;
import com.rentacar.carservice.dto.request.UpdateFuelTypeRequest;
import com.rentacar.carservice.dto.response.FuelTypeResponse;
import com.rentacar.carservice.dto.soap.CreateFuelTypeRequestSOAP;
import com.rentacar.carservice.entity.FuelType;
import com.rentacar.carservice.repository.IFuelTypeRepository;
import com.rentacar.carservice.service.IFuelTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        fuelType.setId(UUID.randomUUID());
        FuelType savedFuelType = _fuelTypeRepository.save(fuelType);
        return mapFuelTypeToFuelTypeResponse(savedFuelType);
    }

    @Override
    public void createFuelTypeViaSOAP(CreateFuelTypeRequestSOAP request){
        FuelType fuelType = new FuelType();
        fuelType.setDeleted(false);
        fuelType.setGas(request.isGas());
        fuelType.setTankCapacity(request.getTankCapacity());
        fuelType.setType(request.getType());
        fuelType.setId(request.getFuelTypeID());
        _fuelTypeRepository.save(fuelType);
        System.out.println("Fuel type saved");
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
        List<FuelType> fuelTypes = _fuelTypeRepository.findAllByDeleted(false);
        return fuelTypes.stream()
                .map(fuelType -> mapFuelTypeToFuelTypeResponse(fuelType))
                .collect(Collectors.toList());
    }

    @Override
    public List<FuelTypeResponse> getAllFuelTypesWithFilter(GetFuelTypesWithFilterRequest request)  {
        List<FuelType> allFuelTypes = _fuelTypeRepository.findAllByDeleted(false);
        return allFuelTypes
                .stream()
                .filter(fuelType -> {
                    if(request.getType() != null) {
                        return fuelType.getType().toLowerCase().contains(request.getType().toLowerCase());
                    } else {
                        return true;
                    }
                })
                .filter(fuelType -> {
                    if(request.getTankCapacity() != null) {
                        return fuelType.getTankCapacity().toLowerCase().contains(request.getTankCapacity().toLowerCase());
                    } else {
                        return true;
                    }
                })
                .map(ft -> mapFuelTypeToFuelTypeResponse(ft))
                .collect(Collectors.toList());
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
