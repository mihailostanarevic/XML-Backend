package com.rentacar.carservice.service.impl;

import com.rentacar.CoreAPI.commands.CreateAdCommand;
import com.rentacar.CoreAPI.dto.*;
import com.rentacar.carservice.client.AuthClient;
import com.rentacar.carservice.dto.client.AdClientResponse;
import com.rentacar.carservice.dto.client.AdCreationDateDTO;
import com.rentacar.carservice.dto.feignClient.AgentDTO;
import com.rentacar.carservice.dto.request.AddAdRequest;
import com.rentacar.carservice.dto.response.*;
import com.rentacar.carservice.dto.soap.CreateAdSOAP;
import com.rentacar.carservice.entity.*;
import com.rentacar.carservice.repository.*;
import com.rentacar.carservice.service.IAdService;
import com.rentacar.carservice.util.exception.GeneralException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.rentacar.carservice.dto.feignClient.CarResponse;

import javax.inject.Inject;
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
    private final AuthClient _authClient;
    private final Logger logger = LoggerFactory.getLogger("Ad service app: " + AdService.class);

    @Inject
    private transient CommandGateway commandGateway;

    public AdService(IAdRepository adRepository, ICarModelRepository carModelRepository, IGearshiftTypeRepository gearshiftTypeRepository, IFuelTypeRepository fuelTypeRepository, ICarRepository carRepository, IPhotoRepository photoRepository, AuthClient authClient) {
        _adRepository = adRepository;
        _carModelRepository = carModelRepository;
        _gearshiftTypeRepository = gearshiftTypeRepository;
        _fuelTypeRepository = fuelTypeRepository;
        _carRepository = carRepository;
        _photoRepository = photoRepository;
        _authClient = authClient;
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
    public AdClientResponse getAd(UUID adId) {
        Ad ad = _adRepository.findOneById(adId);
        AgentDTO agentDTO = _authClient.getAgent(ad.getAgent());
        if(ad != null) {
            AdClientResponse adClientResponse = new AdClientResponse();
            AdResponse adResponse = new AdResponse();
            CarResponse carResponse = new CarResponse();
            adResponse.setId(ad.getId());
            adResponse.setAgentID(ad.getAgent());
            adResponse.setAvailableKilometersPerRent(ad.getAvailableKilometersPerRent());
            adResponse.setCdw(ad.isCdw());
            adResponse.setLimitedDistance(ad.isLimitedDistance());
            adResponse.setName(ad.getCar().getCarModel().getCarBrand().getName() + " " + ad.getCar().getCarModel().getName());
            adResponse.setSeats(ad.getSeats());
            adResponse.setAgentName(agentDTO.getAgentName());
            List<AddressDTO> addressDTOS = new ArrayList<>();
            adResponse.setFullLocations(addressDTOS);
            adClientResponse.setAdResponse(adResponse);
            return adClientResponse;
        }
        return null;
    }

    @Override
    public AgentResponse getAgentByAdID(UUID id) {
        Ad ad = _adRepository.findOneById(id);
        AgentResponse agentResponse = new AgentResponse();
        agentResponse.setId(ad.getAgent());
        return agentResponse;
    }

    @Override
    public CarResponse getCarFromAd(UUID id) {
        Ad ad = _adRepository.getOne(id);

        CarResponse retVal = new CarResponse();
        retVal.setCarID(ad.getCar().getId());
        retVal.setCarBrandName(ad.getCar().getCarModel().getCarBrand().getName());
        retVal.setCarModelName(ad.getCar().getCarModel().getName());
        retVal.setCarClassDescription(ad.getCar().getCarModel().getCarClass().getDescription());
        retVal.setGas(ad.getCar().getFuelType().isGas());
        retVal.setCarClassName(ad.getCar().getCarModel().getCarClass().getName());
        retVal.setCarFuelType(ad.getCar().getFuelType().getType());
        retVal.setCarGearshiftType(ad.getCar().getGearshiftType().getType());
        retVal.setCarNumberOfGears(ad.getCar().getGearshiftType().getNumberOfGears());
        retVal.setCarTankCapacity(ad.getCar().getFuelType().getTankCapacity());
        retVal.setKilometersTraveled(ad.getCar().getKilometersTraveled());

        return retVal;
    }

    @Override
    public AdCreationDateDTO getDateOfCreation(UUID id) {
        Ad ad = _adRepository.getOne(id);
        return new AdCreationDateDTO(ad.getCreationDate());
    }

    @Override
    public List<AdResponse> getAgentAds(UUID id) {
        List<AdResponse> retAdResponseList = new ArrayList<>();
        for (Ad ad : _adRepository.findAllByAgent(id)) {
            retAdResponseList.add(mapAdToAdResponse(ad));
        }
        return retAdResponseList;
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
        long startTime = System.nanoTime();
        CarModel carModel = findCarModel(request.getCarModel());
        GearshiftType gearshiftType = findGearshiftType(request.getGearshiftType());
        FuelType fuelType = findFuelType(request.getFuelType());
        Car car = new Car();
        car.setCarModel(carModel);
        car.setGearshiftType(gearshiftType);
        car.setFuelType(fuelType);
        car.setKilometersTraveled(request.getKilometersTraveled());
        car.setId(UUID.randomUUID());
        Car savedCar = _carRepository.save(car);

        Ad ad = new Ad();
        if(request.isSimpleUser()) {
            SimpleUserAgentIdResponse agentId =  _authClient.getAgentIDFromSimpleUser(request.getAgentId());
            List<Ad> simpleUserListOfAds = _adRepository.findAllByAgent(agentId.getAgentId());
            if(simpleUserListOfAds.size() == 3) {
                throw new GeneralException("You have reached the max limit of 3 created ads.", HttpStatus.BAD_REQUEST);
            }
            ad.setAgent(agentId.getAgentId());
        } else {
            ad.setAgent(request.getAgentId());
        }
        ad.setCar(savedCar);
        ad.setLimitedDistance(request.isLimitedDistance());
        ad.setAvailableKilometersPerRent(request.getAvailableKilometersPerRent());
        ad.setSeats(request.getSeats());
        ad.setCdw(request.isCdw());
        ad.setId(UUID.randomUUID());
        _adRepository.save(ad);
        for (MultipartFile file : fileList) {
            Photo photo = new Photo();
            photo.setAd(ad);
            photo.setName(file.getOriginalFilename());
            photo.setType(file.getContentType());
            photo.setPicByte(compressBytes(file.getBytes()));
            _photoRepository.save(photo);
        }

        AdSaga adSaga = createAdCommand(request, fileList);
        commandGateway.send(new CreateAdCommand(ad.getId(), adSaga));

        return mapAdToAdResponse(ad);
    }

    @Override
    public void createAdViaSOAP(CreateAdSOAP request){
        CarModel carModel = findCarModel(request.getCarModel());

        GearshiftType gearshiftType = findGearshiftType(request.getGearshiftType());
        FuelType fuelType = findFuelType(request.getFuelType());
        Car car = new Car();
        car.setCarModel(carModel);
        car.setGearshiftType(gearshiftType);
        car.setFuelType(fuelType);
        car.setKilometersTraveled(request.getKilometersTraveled());
        car.setId(UUID.fromString(request.getAvailableKilometersPerRent().split(",")[1]));
        Car savedCar = _carRepository.save(car);

        Ad ad = new Ad();
        if(request.isSimpleUser()) {
            SimpleUserAgentIdResponse agentId =  _authClient.getAgentIDFromSimpleUser(request.getAgentId());
            List<Ad> simpleUserListOfAds = _adRepository.findAllByAgent(agentId.getAgentId());
            if(simpleUserListOfAds.size() == 3) {
                throw new GeneralException("You have reached the max limit of 3 created ads.", HttpStatus.BAD_REQUEST);
            }
            ad.setAgent(agentId.getAgentId());
        } else {
            ad.setAgent(request.getAgentId());
        }
        ad.setCar(savedCar);
        ad.setLimitedDistance(request.isLimitedDistance());
        ad.setAvailableKilometersPerRent(request.getAvailableKilometersPerRent().split(",")[0]);
        ad.setSeats(request.getSeats());
        ad.setCdw(request.isCdw());
        ad.setId(request.getAdID());
        _adRepository.save(ad);
    }

    private AdSaga createAdCommand(AddAdRequest request, List<MultipartFile> fileList) {
        CarDetails carDetails = createCarDetails(request);
        AdDetails adDetails = createAdDetails(request);
        PhotoDetailsList photosDetails = createPhotosDetails(request, fileList);
        return createAdSaga(carDetails, adDetails, photosDetails);
    }

    private AdSaga createAdSaga(CarDetails carDetails, AdDetails adDetails, PhotoDetailsList photosDetails) {
        return new AdSaga(carDetails, adDetails, photosDetails);
    }

    private AdDetails createAdDetails(AddAdRequest request) {
        AdDetails adDetails = new AdDetails();
        adDetails.setAgentId(request.getAgentId());
        adDetails.setAvailableKilometersPerRent(request.getAvailableKilometersPerRent());
        adDetails.setKilometersTraveled(request.getKilometersTraveled());
        adDetails.setCdw(request.isCdw());
        adDetails.setLimitedDistance(request.isLimitedDistance());
        adDetails.setSeats(request.getSeats());
        return adDetails;
    }

    private CarDetails createCarDetails(AddAdRequest request) {
        CarDetails carDetails = new CarDetails();
        carDetails.setCarModelName(request.getCarModel());
        carDetails.setGearshiftTypeName(request.getGearshiftType());
        carDetails.setFuelTypeName(request.getFuelType());
        carDetails.setKilometersTraveled(request.getKilometersTraveled());
        return carDetails;
    }

    private PhotoDetailsList createPhotosDetails(AddAdRequest request, List<MultipartFile> fileList) {
        PhotoDetailsList photoDetailsList = new PhotoDetailsList();
        for (MultipartFile file : fileList) {
            PhotoDetails photoDetails = new PhotoDetails();
            photoDetails.setName(file.getName());
            photoDetails.setType(file.getContentType());
            try {
                photoDetails.setPicByte(compressBytes(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            photoDetailsList.getPhotosDetails().add(photoDetails);
        }
        return photoDetailsList;
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
        AdResponse adResponse = new AdResponse();
        adResponse.setId(ad.getId());
        adResponse.setAgentID(ad.getAgent());
        adResponse.setAvailableKilometersPerRent(ad.getAvailableKilometersPerRent());
        adResponse.setCdw(ad.isCdw());
        adResponse.setLimitedDistance(ad.isLimitedDistance());
        adResponse.setSeats(ad.getSeats());
        adResponse.setName(ad.getCar().getCarModel().getCarBrand().getName() + " " + ad.getCar().getCarModel().getName());
        List<AddressDTO> fullLocations = new ArrayList<>();

        String address = _authClient.getAgentAddress(ad.getAgent());
        String[] addressSplited = address.split(",");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity(addressSplited[1].trim());
        addressDTO.setStreet(addressSplited[2].trim());
        addressDTO.setNumber(Integer.parseInt(addressSplited[3].trim()));
        fullLocations.add(addressDTO);
        adResponse.setFullLocations(fullLocations);
        return adResponse;
    }

}
