package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.AddPhotoRequest;
import com.rentacar.carservice.dto.response.PhotoResponse;
import com.rentacar.carservice.service.IPhotoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/photos")
public class PhotoController {

    private final IPhotoService _photoService;

    public PhotoController(IPhotoService photoService) {
        _photoService = photoService;
    }

    @PostMapping
    public PhotoResponse addComment(@RequestBody AddPhotoRequest request) throws Exception{
        return _photoService.addPhoto(request);
    }

    @DeleteMapping("/{id}/photo")
    public void deletePhoto(@PathVariable UUID id) throws Exception{
        _photoService.deletePhoto(id);
    }

    @GetMapping("/{id}/photo")
    public PhotoResponse getPhoto(@PathVariable UUID id) throws Exception{
        return _photoService.getPhoto(id);
    }

    @GetMapping
    public List<PhotoResponse> getAllPhotos() throws Exception{
        return _photoService.getAllPhotos();
    }

    @GetMapping("/{id}/car")
    public List<PhotoResponse> getAllPhotosByCar(@PathVariable UUID id) throws Exception{
        return _photoService.getAllPhotosByCar(id);
    }
}
