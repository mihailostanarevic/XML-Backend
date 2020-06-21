package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.ApproveDenyAccessoryRequest;

public interface IMessageCarAccessoryService {

    void approveDenyAccessory(ApproveDenyAccessoryRequest request);
}
