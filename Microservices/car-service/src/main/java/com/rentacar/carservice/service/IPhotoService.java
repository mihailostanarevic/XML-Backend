package com.rentacar.carservice.service;


import com.rentacar.carservice.dto.request.AddPhotoRequest;
import com.rentacar.carservice.dto.response.PhotoResponse;

import java.util.List;
import java.util.UUID;

public interface IPhotoService {

    PhotoResponse addPhoto(AddPhotoRequest request) throws Exception;

    void deletePhoto(UUID id) throws Exception;

    PhotoResponse getPhoto(UUID id) throws Exception;

    List<PhotoResponse> getAllPhotos() throws Exception;

    List<PhotoResponse> getAllPhotosByCar(UUID id) throws Exception;
}
