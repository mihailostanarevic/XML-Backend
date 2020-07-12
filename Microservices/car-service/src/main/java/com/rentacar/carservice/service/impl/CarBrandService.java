package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.CreateCarBrandRequest;
import com.rentacar.carservice.dto.request.GetCarBrandsFilterRequest;
import com.rentacar.carservice.dto.request.UpdateCarBrandRequest;
import com.rentacar.carservice.dto.response.CarBrandResponse;
import com.rentacar.carservice.dto.soap.CreateCarBrandSOAP;
import com.rentacar.carservice.entity.CarBrand;
import com.rentacar.carservice.repository.ICarBrandRepository;
import com.rentacar.carservice.service.ICarBrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarBrandService implements ICarBrandService {

    private final ICarBrandRepository _carBrandRepository;

    public CarBrandService(ICarBrandRepository carBrandRepository) {
        _carBrandRepository = carBrandRepository;
    }

    @Override
    public CarBrandResponse createCarBrand(CreateCarBrandRequest request) throws Exception {
        CarBrand carBrand = new CarBrand();
        carBrand.setDeleted(false);
        carBrand.setName(request.getName());
        carBrand.setCountry(request.getCountry());
        CarBrand savedCarBrand = _carBrandRepository.save(carBrand);
        return mapCarBrandToCarBrandResponse(savedCarBrand);
    }

    @Override
    public void createCarBrandViaSOAP(CreateCarBrandSOAP request){
        CarBrand carBrand = new CarBrand();
        carBrand.setDeleted(request.isDeleted());
        carBrand.setName(request.getName());
        carBrand.setCountry(request.getCountry());
        carBrand.setId(request.getBrandID());
        _carBrandRepository.save(carBrand);
        System.out.println("Car brand saved");
    }

    @Override
    public CarBrandResponse updateCarBrand(UpdateCarBrandRequest request, UUID id) throws Exception {
        CarBrand carBrand = _carBrandRepository.findOneById(id);
        carBrand.setName(request.getName());
        carBrand.setCountry(request.getCountry());
        CarBrand savedCarBrand = _carBrandRepository.save(carBrand);
        return mapCarBrandToCarBrandResponse(savedCarBrand);
    }

    @Override
    public void deleteCarBrand(UUID id) throws Exception {
        CarBrand carBrand = _carBrandRepository.findOneById(id);
        carBrand.setDeleted(true);
        _carBrandRepository.save(carBrand);
    }

    @Override
    public CarBrandResponse getCarBrand(UUID id) throws Exception {
        CarBrand carBrand = _carBrandRepository.findOneById(id);
        return mapCarBrandToCarBrandResponse(carBrand);
    }

    @Override
    public List<CarBrandResponse> getAllCarBrands() throws Exception {
        List<CarBrand> carBrands = _carBrandRepository.findAllByDeleted(false);
        return  carBrands.stream()
                .map(carBrand -> mapCarBrandToCarBrandResponse(carBrand))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarBrandResponse> getAllCarBrandsWithFilter(GetCarBrandsFilterRequest request) {
        List<CarBrand> allCarBrands = _carBrandRepository.findAllByDeleted(false);
        return allCarBrands
                .stream()
                .filter(carBrand -> {
                    if(request.getBrandName() != null) {
                        return carBrand.getName().toLowerCase().contains(request.getBrandName().toLowerCase());
                    } else {
                        return true;
                    }
                })
                .filter(carBrand -> {
                    if(request.getBrandCountry() != null) {
                        return carBrand.getCountry().toLowerCase().contains(request.getBrandCountry().toLowerCase());
                    } else {
                        return true;
                    }
                })
                .map(cb -> mapCarBrandToCarBrandResponse(cb))
                .collect(Collectors.toList());
    }

    private CarBrandResponse mapCarBrandToCarBrandResponse(CarBrand carBrand) {
        CarBrandResponse response = new CarBrandResponse();
        response.setId(carBrand.getId());
        response.setName(carBrand.getName());
        response.setCountry(carBrand.getCountry());
        return response;
    }
}
