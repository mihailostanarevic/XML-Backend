package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.AddAdRequest;
import com.rentacar.carservice.dto.response.AdResponse;
import com.rentacar.carservice.dto.response.AddressDTO;
import com.rentacar.carservice.dto.response.PhotoResponse;
import com.rentacar.carservice.entity.*;
import com.rentacar.carservice.repository.*;
import com.rentacar.carservice.service.IAdService;
import com.rentacar.carservice.util.exception.GeneralException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal", "unused", "RedundantThrows"})
@Service
public class AdService implements IAdService {

    private final IAdRepository _adRepository;
    private final ICarModelRepository _carModelRepository;
    private final IGearshiftTypeRepository _gearshiftTypeRepository;
    private final IFuelTypeRepository _fuelTypeRepository;
    private final ICarRepository _carRepository;
    private final IPhotoRepository _photoRepository;

    public AdService(IAdRepository adRepository, ICarModelRepository carModelRepository, IGearshiftTypeRepository gearshiftTypeRepository, IFuelTypeRepository fuelTypeRepository, ICarRepository carRepository, IPhotoRepository photoRepository) {
        _adRepository = adRepository;
        _carModelRepository = carModelRepository;
        _gearshiftTypeRepository = gearshiftTypeRepository;
        _fuelTypeRepository = fuelTypeRepository;
        _carRepository = carRepository;
        _photoRepository = photoRepository;
    }

    @Override
    public PhotoResponse getPhoto(UUID adId) {
        Ad ad = _adRepository.findOneById(adId);
        Photo photo = ad.getAdPhotos().iterator().next();   // bilo koja slika
        Photo img = new Photo(photo.getName(), photo.getType(), decompressBytes(photo.getPicByte()), false, ad);
        return mapToPhotoResponse(img);
    }

    @Override
    public List<PhotoResponse> getAllPhotos(UUID adID){
        List<PhotoResponse> retVal = new ArrayList<>();
        Ad ad = _adRepository.findOneById(adID);
        Set<Photo> photos = ad.getAdPhotos();
        for(Photo photo : photos){
            Photo img = new Photo(photo.getName(), photo.getType(), decompressBytes(photo.getPicByte()), false, ad);
            retVal.add(mapToPhotoResponse(img));
        }
        return retVal;
    }

    @Override
    public List<AdResponse> getAgentAds(UUID id) {
        // TODO get agent by ID
//        Agent agent = _agentRepository.findOneById(id);
//        List<AdResponse> retAdResponseList = new ArrayList<>();
//        if(agent != null) {
//            for (Ad agentAd : agent.getAd()) {
//                retAdResponseList.add(mapAdToAdResponse(agentAd));
//            }
//        }
//        return retAdResponseList;
        return null;
    }

    private PhotoResponse mapToPhotoResponse(Photo img) {
        PhotoResponse photoResponse = new PhotoResponse();
        photoResponse.setName(img.getName());
        photoResponse.setType(img.getType());
        photoResponse.setPicByte(img.getPicByte());
        return photoResponse;
    }

    @Override
    public AdResponse createAd(List<MultipartFile> fileList, AddAdRequest request) throws GeneralException, IOException {
        CarModel carModel = findCarModel(request.getCarModel());
        GearshiftType gearshiftType = findGearshiftType(request.getGearshiftType());
        FuelType fuelType = findFuelType(request.getFuelType());
        Car car = new Car();
        car.setCarModel(carModel);
        car.setGearshiftType(gearshiftType);
        car.setFuelType(fuelType);
        car.setKilometersTraveled(request.getKilometersTraveled());
        Car savedCar = _carRepository.save(car);
        Ad ad = new Ad();
        ad.setAgent(request.getAgentId());
        ad.setCar(savedCar);
        ad.setLimitedDistance(request.isLimitedDistance());
        ad.setAvailableKilometersPerRent(request.getAvailableKilometersPerRent());
        ad.setSeats(request.getSeats());
        ad.setCdw(request.isCdw());
        _adRepository.save(ad);
        for (MultipartFile file : fileList) {
            Photo photo = new Photo();
            photo.setAd(ad);
            photo.setName(file.getOriginalFilename());
            photo.setType(file.getContentType());
            photo.setPicByte(compressBytes(file.getBytes()));
            _photoRepository.save(photo);
        }

        return mapAdToAdResponse(ad);
    }


    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    private CarModel findCarModel(String carModelString) {
        String[] carModelArray = carModelString.split(",");
        String carBrand = carModelArray[0].trim();
        String carModelName = carModelArray[1].trim();
        String carClass = carModelArray[2].trim();
        for (CarModel carModel : _carModelRepository.findAll()) {
            if(carModel.getCarBrand().getName().equalsIgnoreCase(carBrand)
                    && carModel.getName().equalsIgnoreCase(carModelName)
                    && carModel.getCarClass().getName().equalsIgnoreCase(carClass)){
                return carModel;
            }
        }
        return null;
    }

    private GearshiftType findGearshiftType(String gearshifTypeString) {
        String[] gearshiftTypeArray = gearshifTypeString.split(",");
        String type = gearshiftTypeArray[0].trim();
        String numberOfGears = gearshiftTypeArray[1].trim();
        for (GearshiftType gearshiftType : _gearshiftTypeRepository.findAll()) {
            if(gearshiftType.getType().equalsIgnoreCase(type)
                    && gearshiftType.getNumberOfGears().equalsIgnoreCase(numberOfGears)) {
                return gearshiftType;
            }
        }
        return null;
    }

    private FuelType findFuelType(String fuelTypeString) {
        String[] fuelTypeArray = fuelTypeString.split(",");
        String type = fuelTypeArray[0].trim();
        String tankCapacity = fuelTypeArray[1].trim();
        String gas_string = fuelTypeArray[2].trim();
        boolean gas = false;
        if(gas_string.trim().equalsIgnoreCase("gas")) {
            gas = true;
        }
        for (FuelType fuelType : _fuelTypeRepository.findAll()) {
            if(fuelType.getType().equalsIgnoreCase(type)
                    && fuelType.getTankCapacity().equalsIgnoreCase(tankCapacity)
                    && fuelType.isGas() == gas) {
                return fuelType;
            }
        }
        return null;
    }

    private AdResponse mapAdToAdResponse(Ad ad) {
        // TODO get agent by AD
        AdResponse adResponse = new AdResponse();
        adResponse.setId(ad.getId());
//        adResponse.setAgentID(ad.getAgent().getId());
        adResponse.setAvailableKilometersPerRent(ad.getAvailableKilometersPerRent());
        adResponse.setCdw(ad.isCdw());
        adResponse.setLimitedDistance(ad.isLimitedDistance());
        adResponse.setSeats(ad.getSeats());
        adResponse.setName(ad.getCar().getCarModel().getCarBrand().getName() + " " + ad.getCar().getCarModel().getName());
        List<AddressDTO> fullLocations = new ArrayList<>();
//        for (Address address : ad.getAgent().getAddress()) {
//            AddressDTO addressDTO = new AddressDTO();
//            addressDTO.setId(address.getId());
//            addressDTO.setCity(address.getCity());
//            addressDTO.setStreet(address.getStreet());
//            addressDTO.setNumber(address.getNumber());
//            fullLocations.add(addressDTO);
//        }
        adResponse.setFullLocations(fullLocations);
        return adResponse;
    }
}
