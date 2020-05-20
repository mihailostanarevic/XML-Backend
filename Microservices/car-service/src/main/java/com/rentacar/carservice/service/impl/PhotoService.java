package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.AddPhotoRequest;
import com.rentacar.carservice.dto.response.PhotoResponse;
import com.rentacar.carservice.repository.IPhotoRepository;
import com.rentacar.carservice.service.IPhotoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhotoService implements IPhotoService {

    private final IPhotoRepository _photoRepository;

    public PhotoService(IPhotoRepository photoRepository) {
        _photoRepository = photoRepository;
    }

    @Override
    public PhotoResponse addPhoto(AddPhotoRequest request) throws Exception {
        return null;
    }

    @Override
    public void deletePhoto(UUID id) throws Exception {

    }

    @Override
    public PhotoResponse getPhoto(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<PhotoResponse> getAllPhotos() throws Exception {
        return null;
    }

    @Override
    public List<PhotoResponse> getAllPhotosByCar(UUID id) throws Exception {
        return null;
    }
}
