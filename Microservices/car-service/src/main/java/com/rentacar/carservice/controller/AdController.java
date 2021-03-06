package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.client.AdClientResponse;
import com.rentacar.carservice.dto.client.AdCreationDateDTO;
import com.rentacar.carservice.dto.request.AddAdRequest;
import com.rentacar.carservice.dto.response.AdResponse;
import com.rentacar.carservice.dto.response.AgentResponse;
import com.rentacar.carservice.dto.feignClient.CarResponse;
import com.rentacar.carservice.dto.response.PhotoResponse;
import com.rentacar.carservice.service.IAdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/{id}")
    public ResponseEntity<AdClientResponse> getAdByID(@PathVariable("id") UUID adId){
        return new ResponseEntity<>(_adService.getAd(adId), HttpStatus.OK);
    }

    @PostMapping("/image")
    @PreAuthorize("hasAuthority('VIEW_IMAGE')")
    public ResponseEntity<?> image(@RequestParam("imageFile") List<MultipartFile> file) throws Exception{
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @PostMapping(consumes = { "multipart/form-data" })
    @PreAuthorize("hasAuthority('CREATE_AD')")
    public ResponseEntity<?> createAd(@RequestPart("imageFile") List<MultipartFile> fileList, @RequestPart("request") AddAdRequest request) throws Exception{
        return new ResponseEntity<>(_adService.createAd(fileList, request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/image" )
    @PreAuthorize("hasAuthority('VIEW_IMAGE')")
    public ResponseEntity<PhotoResponse> getImage(@PathVariable("id") UUID adId) {
        return new ResponseEntity<>(_adService.getPhoto(adId), HttpStatus.OK);
    }

    @GetMapping("/{id}/ads")
    @PreAuthorize("hasAuthority('VIEW_AD')")
    public List<AdResponse> getAgentAds(@PathVariable("id") UUID agentId) throws Exception{
        return _adService.getAgentAds(agentId);
    }

    @GetMapping("/{id}/agent")
//    @PreAuthorize("hasAuthority('VIEW_AD')")
    public ResponseEntity<AgentResponse> getAgentIDByAdID(@PathVariable("id") UUID id) throws Exception{
        return new ResponseEntity<>(_adService.getAgentByAdID(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/car")
    public CarResponse getCarFromAd(@PathVariable("id") UUID id){
        return _adService.getCarFromAd(id);
    }

    @GetMapping("/{id}/creation-date")
    AdCreationDateDTO getDateOfCreation(@PathVariable("id") UUID id){
        return _adService.getDateOfCreation(id);
    }
}
