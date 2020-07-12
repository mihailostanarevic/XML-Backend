package com.rentacar.carservice.soap;

import com.rentacar.carservice.dto.soap.*;
import com.rentacar.carservice.model.*;
import com.rentacar.carservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Endpoint
public class CarEndpoint implements WSEndpoint {

    private ObjectFactory objectFactory;

    private final IGearshiftTypeService _gearshiftService;

    private final IFuelTypeService _fuelTypeService;

    private final ICarService _carService;

    private final ICarBrandService _carBrandService;

    private final ICarClassService _carClassService;

    private final ICarModelService _carModelService;

    private final ICarAccessoriesService _carAccessoriesService;

    private final IAdService _adService;

    @Autowired
    public CarEndpoint(IGearshiftTypeService gearshiftService, IFuelTypeService fuelTypeService, ICarService carService, ICarBrandService carBrandService, ICarClassService carClassService, ICarModelService carModelService, ICarAccessoriesService carAccessoriesService, IAdService adService) {
        _gearshiftService = gearshiftService;
        _fuelTypeService = fuelTypeService;
        _carService = carService;
        _carBrandService = carBrandService;
        _carClassService = carClassService;
        _carModelService = carModelService;
        _carAccessoriesService = carAccessoriesService;
        _adService = adService;
        this.objectFactory = new ObjectFactory();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createGearshiftTypeRequest")
    @ResponsePayload
    public void createGearshiftType(@RequestPayload JAXBElement<CreateGearshiftTypeRequestDTO> request) {
        _gearshiftService.createGearshiftTypeViaSOAP(request.getValue());
        System.out.println("Uspeo sam");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createFuelType")
    @ResponsePayload
    public void createFuelType(@RequestPayload JAXBElement<FuelType> request) throws Exception {
        CreateFuelTypeRequestSOAP requestDTO = new CreateFuelTypeRequestSOAP();
        requestDTO.setType(request.getValue().getType());
        requestDTO.setTankCapacity(request.getValue().getTankCapacity());
        requestDTO.setGas(request.getValue().isGas());
        requestDTO.setFuelTypeID(request.getValue().getFuelTypeID());
        _fuelTypeService.createFuelTypeViaSOAP(requestDTO);
        System.out.println("Uspeo sam");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createCar")
    @ResponsePayload
    public void createCar(@RequestPayload JAXBElement<Car> request) throws Exception {
        CreateCarSOAP requestDTO = new CreateCarSOAP();
        requestDTO.setKilometersTraveled(request.getValue().getKilometersTraveled());
        requestDTO.setCarID(request.getValue().getCarID());
        requestDTO.setCarModelId(request.getValue().getCarModel());
        requestDTO.setFuelTypeId(request.getValue().getFuelType());
        requestDTO.setGearshiftTypeId(request.getValue().getGearshiftType());
        requestDTO.setDeleted(request.getValue().isDeleted());
        _carService.createCarViaSOAP(requestDTO);
        System.out.println("Uspeo sam");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createCarBrand")
    @ResponsePayload
    public void createCarBrand(@RequestPayload JAXBElement<CarBrand> request) throws Exception {
        CreateCarBrandSOAP requestDTO = new CreateCarBrandSOAP();
        requestDTO.setBrandID(request.getValue().getCarBrandID());
        requestDTO.setName(request.getValue().getName());
        requestDTO.setCountry(request.getValue().getCountry());
        requestDTO.setDeleted(request.getValue().isDeleted());
        _carBrandService.createCarBrandViaSOAP(requestDTO);
        System.out.println("Uspeo sam");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createCarClass")
    @ResponsePayload
    public void createCarClass(@RequestPayload JAXBElement<CarClass> request) throws Exception {
        System.out.println("Pogodio sam endpoint");
        CreateCarClassSOAP requestDTO = new CreateCarClassSOAP();
        requestDTO.setCarClassID(request.getValue().getCarClassID());
        requestDTO.setName(request.getValue().getName());
        requestDTO.setDescription(request.getValue().getDescription());
        requestDTO.setDeleted(request.getValue().isDeleted());
        _carClassService.createCarClassViaSOAP(requestDTO);
        System.out.println("Uspeo sam");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createCarModel")
    @ResponsePayload
    public void createCarModel(@RequestPayload JAXBElement<CarModel> request) throws Exception {
        CreateCarModelSOAP requestDTO = new CreateCarModelSOAP();
        requestDTO.setCarModelID(request.getValue().getCarModelID());
        requestDTO.setCarBrand(request.getValue().getCarBrand());
        requestDTO.setCarClass(request.getValue().getCarClass());
        requestDTO.setDeleted(request.getValue().isDeleted());
        requestDTO.setName(request.getValue().getName());
        _carModelService.createCarModelViaSOAP(requestDTO);
        System.out.println("Uspeo sam");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createCarAccessory")
    @ResponsePayload
    public void createCarAccessories(@RequestPayload JAXBElement<CarAccessories> request) throws Exception {
        CreateCarAccessoriesSOAP requestDTO = new CreateCarAccessoriesSOAP();
        requestDTO.setCarAccessoriesID(request.getValue().getCarAccessoriesID());
        requestDTO.setDescription(request.getValue().getDescription());
        requestDTO.setDeleted(request.getValue().isDeleted());
        _carAccessoriesService.createCarAccessoriesViaSOAP(requestDTO);
        System.out.println("Uspeo sam");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createAd")
    @ResponsePayload
    public void createAd(@RequestPayload JAXBElement<Ad> request) throws Exception {
        CreateAdSOAP requestDTO = new CreateAdSOAP();
        requestDTO.setAdID(request.getValue().getAdID());
        requestDTO.setCarModel(request.getValue().getCarModel());
        requestDTO.setGearshiftType(request.getValue().getGearshiftType());
        requestDTO.setAvailableKilometersPerRent(request.getValue().getAvailableKilometersPerRent());
        requestDTO.setFuelType(request.getValue().getFuelType());
        requestDTO.setCdw(request.getValue().isCdw());
        requestDTO.setDeleted(false);
        requestDTO.setLimitedDistance(request.getValue().isLimitedDistance());
        requestDTO.setAgentId(request.getValue().getAgentId());
        requestDTO.setSeats(request.getValue().getSeats());
        requestDTO.setSimpleUser(request.getValue().isSimpleUser());
        requestDTO.setKilometersTraveled(request.getValue().getKilometersTraveled());
        _adService.createAdViaSOAP(requestDTO);
        System.out.println("Uspeo sam");
    }
}
