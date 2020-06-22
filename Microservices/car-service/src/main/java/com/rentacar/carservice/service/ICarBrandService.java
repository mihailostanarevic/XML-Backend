package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.CreateCarBrandRequest;
import com.rentacar.carservice.dto.request.GetCarBrandsFilterRequest;
import com.rentacar.carservice.dto.request.UpdateCarBrandRequest;
import com.rentacar.carservice.dto.response.CarBrandResponse;

import java.util.List;
import java.util.UUID;

public interface ICarBrandService {

    CarBrandResponse createCarBrand(CreateCarBrandRequest request) throws Exception;

    CarBrandResponse updateCarBrand(UpdateCarBrandRequest request, UUID id) throws Exception;

    void deleteCarBrand(UUID id) throws Exception;

    CarBrandResponse getCarBrand(UUID id) throws Exception;

    List<CarBrandResponse> getAllCarBrands() throws Exception;

    List<CarBrandResponse> getAllCarBrandsWithFilter(GetCarBrandsFilterRequest request);
}
