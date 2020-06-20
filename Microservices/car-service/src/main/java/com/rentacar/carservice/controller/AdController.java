package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.AddAdRequest;
import com.rentacar.carservice.dto.request.RequestDTO;
import com.rentacar.carservice.dto.request.UpdateAdRequest;
import com.rentacar.carservice.dto.request.UpdateCarAvailability;
import com.rentacar.carservice.dto.response.AdResponse;
import com.rentacar.carservice.dto.response.PhotoResponse;
import com.rentacar.carservice.dto.response.RequestResponse;
import com.rentacar.carservice.service.IAdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/ads")
public class AdController {

    private final IAdService _adService;

    public AdController(IAdService adService) {
        _adService = adService;
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return new ResponseEntity<>("Hello from ads service", HttpStatus.OK);
    }

    @PostMapping("/image")
    public ResponseEntity<?> image(@RequestParam("imageFile") List<MultipartFile> file) throws Exception{
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<?> addAd(@RequestPart("imageFile") List<MultipartFile> fileList, @RequestPart("request") AddAdRequest request) throws Exception{
        return new ResponseEntity<>(_adService.createAd(fileList, request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/image" )
    public ResponseEntity<PhotoResponse> getImage(@PathVariable("id") UUID adId) {
        return new ResponseEntity<>(_adService.getPhoto(adId), HttpStatus.OK);
    }

}
