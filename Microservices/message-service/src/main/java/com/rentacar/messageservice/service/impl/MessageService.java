package com.rentacar.messageservice.service.impl;

import com.rentacar.messageservice.dto.request.SendEmailRequest;
import com.rentacar.messageservice.dto.response.EmailSentResponse;
import com.rentacar.messageservice.service.IMessageService;
import org.springframework.stereotype.Service;


@Service
public class MessageService implements IMessageService {

    @Override
    public EmailSentResponse sendMessage(SendEmailRequest request){
        return null;
    }
}
