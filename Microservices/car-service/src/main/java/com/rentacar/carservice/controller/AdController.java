package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.AddAdRequest;
import com.rentacar.carservice.dto.request.UpdateAdRequest;
import com.rentacar.carservice.dto.request.UpdateCarAvailability;
import com.rentacar.carservice.dto.response.AdResponse;
import com.rentacar.carservice.service.IAdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> addAd(@RequestBody AddAdRequest request) throws Exception {
        _adService.createAd(request);
        return new ResponseEntity<>("successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateAdAvailability(@RequestBody UpdateCarAvailability request) {
        _adService.updateAdAvailability(request);
        return new ResponseEntity<>("successfully created", HttpStatus.OK);
    }

    @PutMapping("/{id}/ad")
    public AdResponse updateAd(@RequestBody UpdateAdRequest request, @PathVariable UUID id) throws Exception{
        return _adService.updateAd(request, id);
    }

    @DeleteMapping("/{id}/ad")
    public void deleteAd(@PathVariable UUID id) throws Exception{
        _adService.deleteAd(id);
    }

    @GetMapping("/{id}/ad")
    public AdResponse getAd(@PathVariable UUID id) throws Exception{
        return _adService.getAd(id);
    }

    @GetMapping
    public List<AdResponse> getAllAds() throws Exception{
        return _adService.getAllAds();
    }

    @GetMapping("/{id}/car-brand")
    public List<AdResponse> getAdByCarBrand(@PathVariable UUID id) throws Exception{
        return _adService.getAllAdsByCarBrand(id);
    }

    @GetMapping("/{id}/car-model")
    public List<AdResponse> getAdByCarModel(@PathVariable UUID id) throws Exception{
        return _adService.getAllAdsByCarModel(id);
    }

    @GetMapping("/{id}/car-class")
    public List<AdResponse> getAdByCarClass(@PathVariable UUID id) throws Exception{
        return _adService.getAllAdsByCarClass(id);
    }

    @GetMapping("/{id}/gearshift-type")
    public List<AdResponse> getAdByGearshiftType(@PathVariable UUID id) throws Exception{
        return _adService.getAllAdsByGearshiftType(id);
    }

    @GetMapping("/{id}/fuel-type")
    public List<AdResponse> getAdByFuelType(@PathVariable UUID id) throws Exception{
        return _adService.getAllAdsByFuelType(id);
    }

    @GetMapping("/with-gas")
    public List<AdResponse> getAllByGas() throws Exception{
        return _adService.getAllAdsByGas();
    }
}
