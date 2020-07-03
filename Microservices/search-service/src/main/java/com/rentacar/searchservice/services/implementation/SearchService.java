package com.rentacar.searchservice.services.implementation;

import com.rentacar.CoreAPI.dto.AdSaga;
import com.rentacar.CoreAPI.dto.PhotoDetails;
import com.rentacar.searchservice.clients.AuthClient;
import com.rentacar.searchservice.clients.RentClient;
import com.rentacar.searchservice.dto.feignClient.AgentDTO;
import com.rentacar.searchservice.dto.feignClient.RequestAdDTO;
import com.rentacar.searchservice.dto.feignClient.RequestDTO;
import com.rentacar.searchservice.dto.response.*;
import com.rentacar.searchservice.entity.*;
import com.rentacar.searchservice.repository.*;
import com.rentacar.searchservice.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
public class SearchService implements ISearchService {

    @Autowired
    private IAdRepository _adRepository;

    @Autowired
    private RentClient _rentClient;

    @Autowired
    private AuthClient _authClient;

    @Autowired
    private ICarModelRepository _carModelRepository;

    @Autowired
    private IGearshiftTypeRepository _gearshiftTypeRepository;

    @Autowired
    private IFuelTypeRepository _fuelTypeRepository;

    @Autowired
    private ICarRepository _carRepository;

    @Autowired
    private IPhotoRepository _photoRepository;

    @Override
    public List<SearchResultResponse> searchAds(String city, String from, String to) {
        List<SearchResultResponse> retVal = new ArrayList<SearchResultResponse>();

        String paramTimeFrom = from.split(" ")[0];
        String paramDateFrom = from.split(" ")[1];
        String paramTimeTo = to.split(" ")[0];
        String paramDateTo = to.split(" ")[1];

        LocalTime timeFrom = LocalTime.parse(paramTimeFrom);
        LocalDate dateFrom = LocalDate.parse(paramDateFrom);
        LocalTime timeTo = LocalTime.parse(paramTimeTo);
        LocalDate dateTo = LocalDate.parse(paramDateTo);

        List<Ad> allAds = _adRepository.findAllByDeleted(false);
        if(!city.equals(""))
            allAds = removeIncorrectCityAd(allAds, city);

        List<RequestDTO> allRequests = _rentClient.getRequestByStatus("APPROVED");
        allAds = ignoreNonRelevantAds(allAds, allRequests, dateFrom, timeFrom, dateTo, timeTo, city);
        List<RequestAdDTO> requestAds = passableRequests(allRequests, dateFrom, timeFrom, dateTo, timeTo, city);
        boolean found = false;

        List<UUID> ids = new ArrayList<>();
        for(Ad ad : allAds){
            ids.add(ad.getId());
        }

        for(Ad ad : allAds){
            found = false;
            for(RequestAdDTO rqAd : requestAds){
                if(rqAd.getAd_id().equals(ad.getId())){
                    found = true;
                    if(ids.contains(rqAd.getAd_id())) {
                        ids.remove(rqAd.getAd_id());
                        SearchResultResponse dto = makeDTO(ad);
                        retVal.add(dto);
                    }
                }
            }

            if(!found){
                SearchResultResponse dto = makeDTO(ad);
                retVal.add(dto);
            }
        }

        return retVal;
    }

    @Override
    public void createAd(UUID adId, AdSaga adSaga) throws Exception {
        Ad ad = _adRepository.findOneById(adId);
        if(ad != null) {
            throw new Exception("Ad already exist!");
        }
        createAdFromAdCommand(adSaga);
    }

    private void createAdFromAdCommand(AdSaga adSaga) {
        Car car = createCarFromAdSaga(adSaga);
        Ad ad = createAdFromAdSaga(adSaga, car);
        createPhotosFromAdSaga(adSaga, ad);
    }

    private void createPhotosFromAdSaga(AdSaga adSaga, Ad ad){
        for (PhotoDetails photoDetails : adSaga.getPhotosDetails().getPhotosDetails()) {
            Photo photo = new Photo();
            photo.setAd(ad);
            photo.setName(photoDetails.getName());
            photo.setType(photoDetails.getType());
            photo.setPicByte(photoDetails.getPicByte());
            _photoRepository.save(photo);
        }
    }

    private Ad createAdFromAdSaga(AdSaga adSaga, Car car) {
        Ad ad = new Ad();
        ad.setAgent(adSaga.getAdDetails().getAgentId());
        ad.setCar(car);
        ad.setLimitedDistance(adSaga.getAdDetails().isLimitedDistance());
        ad.setAvailableKilometersPerRent(adSaga.getAdDetails().getAvailableKilometersPerRent());
        ad.setSeats(adSaga.getAdDetails().getSeats());
        ad.setCdw(adSaga.getAdDetails().isCdw());
        return _adRepository.save(ad);
    }

    private Car createCarFromAdSaga(AdSaga adSaga) {
        Car car = new Car();
        car.setCarModel(findCarModel(adSaga.getCarDetails().getCarModelName()));
        car.setGearshiftType(findGearshiftType(adSaga.getCarDetails().getGearshiftTypeName()));
        car.setFuelType(findFuelType(adSaga.getCarDetails().getFuelTypeName()));
        car.setKilometersTraveled(adSaga.getCarDetails().getKilometersTraveled());
        return _carRepository.save(car);
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

    private SearchResultResponse makeDTO(Ad ad) {
        SearchResultResponse retVal = new SearchResultResponse();
        List<PhotoResponse> photos = getAllPhotos(ad.getId());
        AdSearchResponse adDTO = new AdSearchResponse(ad.getId(), ad.isLimitedDistance(), ad.getSeats(), ad.isCdw(), ad.getCreationDate(), photos);
        CarSearchResponse carDTO = new CarSearchResponse();
        carDTO.setCarID(ad.getCar().getId());
        carDTO.setCarModelName(ad.getCar().getCarModel().getName());
        carDTO.setCarBrandName(ad.getCar().getCarModel().getCarBrand().getName());
        carDTO.setCarClassName(ad.getCar().getCarModel().getCarClass().getName());
        carDTO.setCarClassDesc(ad.getCar().getCarModel().getCarClass().getDescription());
        carDTO.setFuelTypeType(ad.getCar().getFuelType().getType());
        carDTO.setFuelTypeTankCapacity(ad.getCar().getFuelType().getTankCapacity());
        carDTO.setFuelTypeGas(ad.getCar().getFuelType().isGas());
        carDTO.setGearshiftTypeType(ad.getCar().getGearshiftType().getType());
        carDTO.setGetGearshiftTypeNumberOfGears(ad.getCar().getGearshiftType().getNumberOfGears());

        AgentDTO agentdto = _authClient.getAgent(ad.getAgent());
        AgentSearchResponse agentDTO = new AgentSearchResponse(agentdto.getAgentID(), agentdto.getAgentName(), agentdto.getDateFounded(), agentdto.getAddress());

        retVal.setAd(adDTO);
        retVal.setAgent(agentDTO);
        retVal.setCar(carDTO);
        return retVal;
    }

    public List<Ad> removeIncorrectCityAd(List<Ad> allAds, String city){
        return allAds
                .stream()
                .filter(ad -> {
                    if(city != null) {
                        String agentCity = _authClient.getAgentAddress(ad.getAgent());
                        return parseCity(agentCity).toUpperCase().equals(city.toUpperCase());
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Ad> ignoreNonRelevantAds(List<Ad> allAds, List<RequestDTO> allRequests, LocalDate dateFrom, LocalTime timeFrom, LocalDate dateTo, LocalTime timeTo, String city) {
        List<Ad> retVal = new ArrayList<>();
        for (Ad ad : allAds) {
            retVal.add(ad);
        }
        System.out.println("Ne smeju ovi");
        List<UUID> ids = new ArrayList<>();
        for (RequestDTO request : allRequests) {
            if(parseCity(request.getPickUpAddress()).toUpperCase().equals(city.toUpperCase())) {
                for (RequestAdDTO rqAd : request.getRequestAds()) {
                    if (!isCompletelyBefore(rqAd, dateFrom, timeFrom) && !isCompletelyAfter(rqAd, dateTo, timeTo)) {
                        if (!ids.contains(rqAd.getAd_id())) {
                            ids.add(rqAd.getAd_id());
                        }
                    }
                }
            }
        }


        for (Ad ad : allAds) {
            for (UUID id : ids) {
                if (ad.getId().equals(id)) {
                    retVal.remove(ad);
                }
            }
        }

        return retVal;
    }

    private List<RequestAdDTO> passableRequests(List<RequestDTO> allRequests, LocalDate dateFrom, LocalTime timeFrom, LocalDate dateTo, LocalTime timeTo, String city) {
        List<RequestAdDTO> retVal = new ArrayList<>();
        for(RequestDTO request : allRequests){
            if(parseCity(request.getPickUpAddress()).toUpperCase().equals(city.toUpperCase())) {
                for(RequestAdDTO rqAd : request.getRequestAds()) {
                    if(isCompletelyBefore(rqAd, dateFrom, timeFrom) || isCompletelyAfter(rqAd, dateTo, timeTo)){
                        retVal.add(rqAd);
                    }
                }
            }
        }

        return retVal;
    }

    public boolean isCompletelyBefore(RequestAdDTO rqAd, LocalDate dateFrom, LocalTime timeFrom){
        if(rqAd.getReturnDate().isBefore(dateFrom)){
            return true;
        }else {
            if(rqAd.getReturnDate().isEqual(dateFrom) && rqAd.getReturnTime().isBefore(timeFrom)){
                return true;
            }
        }
        return false;
    }

    public boolean isCompletelyAfter(RequestAdDTO rqAd, LocalDate dateTo, LocalTime timeTo){
        if(rqAd.getPickUpDate().isAfter(dateTo)){
            return true;
        }else {
            if(rqAd.getPickUpDate().isEqual(dateTo) && rqAd.getReturnTime().isAfter(timeTo)){
                return true;
            }
        }
        return false;
    }

    public String parseCity(String address){
        return address.split(",")[1];
    }

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

    private PhotoResponse mapToPhotoResponse(Photo img) {
        PhotoResponse photoResponse = new PhotoResponse();
        photoResponse.setName(img.getName());
        photoResponse.setType(img.getType());
        photoResponse.setPicByte(img.getPicByte());
        return photoResponse;
    }
}
