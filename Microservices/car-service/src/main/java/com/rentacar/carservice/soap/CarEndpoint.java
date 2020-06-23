package com.rentacar.carservice.soap;

import com.rentacar.carservice.model.CreateGearshiftTypeRequestDTO;
import com.rentacar.carservice.service.IGearshiftTypeService;
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

    @Autowired
    public CarEndpoint(IGearshiftTypeService gearshiftService) {
        _gearshiftService = gearshiftService;
        this.objectFactory = new ObjectFactory();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createGearshiftTypeRequest")
    @ResponsePayload
    public void createGearshiftType(@RequestPayload JAXBElement<CreateGearshiftTypeRequestDTO> request) {
        System.out.println("usao sam hej");
        Long retVal = _gearshiftService.createGearshiftTypeViaSOAP(request.getValue());
        objectFactory.createCreateGearshiftTypeResponse(retVal);
    }

}
