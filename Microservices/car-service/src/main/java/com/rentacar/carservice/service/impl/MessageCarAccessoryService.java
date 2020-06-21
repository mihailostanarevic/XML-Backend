package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.ApproveDenyAccessoryRequest;
import com.rentacar.carservice.entity.Car;
import com.rentacar.carservice.entity.MessageCarAccessories;
import com.rentacar.carservice.repository.ICarAccessoriesRepository;
import com.rentacar.carservice.repository.ICarRepository;
import com.rentacar.carservice.repository.IMessageCarAccessoriesRepository;
import com.rentacar.carservice.service.IMessageCarAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageCarAccessoryService implements IMessageCarAccessoryService {

    @Autowired
    private IMessageCarAccessoriesRepository _messageCarAccessoriesRepository;

    @Autowired
    private ICarAccessoriesRepository _carAccessoriesRepository;

    @Autowired
    private ICarRepository _carRepository;

    @Override
    public void approveDenyAccessory(ApproveDenyAccessoryRequest request) {
        MessageCarAccessories tuple = _messageCarAccessoriesRepository.getOne(request.getId());
        tuple.setApproved(request.isApproved());

        Car car = _carRepository.getOne(tuple.getMessage().getAd().getCar().getId());
        car.getCarAccessories().add(_carAccessoriesRepository.getOne(tuple.getCar_accessory().getId()));
        tuple.setReviewed(true);
        _messageCarAccessoriesRepository.save(tuple);
    }
}
