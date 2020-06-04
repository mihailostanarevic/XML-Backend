package com.rentacar.messageservice.service;

import com.rentacar.messageservice.dto.request.SendEmailRequest;
import com.rentacar.messageservice.dto.response.EmailSentResponse;

public interface IMessageService {

    EmailSentResponse sendMessage(SendEmailRequest request) throws Exception;
}
