package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.AddAdRequest;
import com.rentacar.carservice.dto.request.UpdateAdRequest;
import com.rentacar.carservice.dto.request.UpdateCarAvailability;
import com.rentacar.carservice.dto.response.AdResponse;
import com.rentacar.carservice.entity.*;
import com.rentacar.carservice.repository.*;
import com.rentacar.carservice.service.IAdService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal", "unused", "RedundantThrows"})
@Service
public class AdService implements IAdService {

    private final IAdRepository _adRepository;
    private final ICarRepository _carRepository;
    private final ICarModelRepository _carModelRepository;
    private final ICarBrandRepository _carBrandRepository;
    private final IGearshiftTypeRepository _gearshiftTypeRepository;
    private final IFuelTypeRepository _fuelTypeRepository;
    private final ICarClassRepository _carClassRepository;
    private final IPhotoRepository _photoRepository;
    private final IDateRepository _dateRepository;

    public AdService(IAdRepository adRepository, ICarRepository carRepository, ICarModelRepository carModelRepository, ICarBrandRepository carBrandRepository, IGearshiftTypeRepository gearshiftTypeRepository, IFuelTypeRepository fuelTypeRepository, ICarClassRepository carClassRepository, IPhotoRepository photoRepository, IDateRepository dateRepository) {
        _adRepository = adRepository;
        _carRepository = carRepository;
        _carModelRepository = carModelRepository;
        _carBrandRepository = carBrandRepository;
        _gearshiftTypeRepository = gearshiftTypeRepository;
        _fuelTypeRepository = fuelTypeRepository;
        _carClassRepository = carClassRepository;
        _photoRepository = photoRepository;
        _dateRepository = dateRepository;
    }

    @Override
    public AdResponse createAd(AddAdRequest request) throws Exception {
        Ad ad = new Ad();
        ad.setPriceFromTo(request.getPriceFrom() + "-" + request.getPriceTo());
        ad.setSeats(request.getSeats());
        ad.setCdw(request.isCdw());
        ad.setLimitedDistance(request.getLimitedDistance());
        ad.setAvailableKilometersPerRent(request.getAvailableKilometersPerRent());
        ad.setAgentID(request.getAgentID());
        for (String url : request.getPhotosUrls()) {
            Photo photo = new Photo();
            photo.setUrl(url);
            _photoRepository.save(photo);
            ad.getPhotos().add(photo);
        }
        Car car = new Car();
        car.setKilometersTraveled(request.getKilometersTraveled());
        car.setGearshiftType(_gearshiftTypeRepository.findOneByType(request.getGearshiftType()));
        car.setFuelType(_fuelTypeRepository.findOneByType(request.getFuelType()));
        car.setCarModel(_carModelRepository.findOneByName(request.getCarModel()));
        Car savedCar = _carRepository.save(car);
        ad.setCar(savedCar);

        _adRepository.save(ad);
        return adToAdResponse(ad);
    }

    @Override
    public AdResponse updateAd(UpdateAdRequest request, UUID id) throws Exception {
        return null;
    }

    @Override
    public void deleteAd(UUID id) throws Exception {

    }

    @Override
    public AdResponse getAd(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAds() throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByCarModel(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByCarBrand(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByCarClass(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByGearshiftType(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByFuelType(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<AdResponse> getAllAdsByGas() throws Exception {
        return null;
    }

    @Override
    public boolean updateAdAvailability(UpdateCarAvailability request) {
        Ad ad = _adRepository.findOneById(request.getCarID());
        if(ad != null) {
            ad.setAvailable(!request.getStatus().equals("rent"));
            Date reservedTerm = new Date();
            reservedTerm.setDateFrom(LocalDate.parse(request.getDateFrom()));   // format -> "2016-06-12"
            reservedTerm.setDateTo(LocalDate.parse(request.getDateTo()));
            reservedTerm.setTimeFrom(LocalTime.parse(request.getTimeFrom()));   // format -> "06:30"
            reservedTerm.setTimeTo(LocalTime.parse(request.getTimeTo()));
            Date reservation = _dateRepository.save(reservedTerm);
            ad.getReservedTerms().add(reservation);
            _adRepository.save(ad);
            return true;
        }
        // TODO odbiti postojece zahteve (komunikacija sa rent)
        return false;
    }

    private AdResponse adToAdResponse(Ad ad) {
        AdResponse adResponse = new AdResponse();
        adResponse.setId(ad.getId());
        return adResponse;
    }
}
