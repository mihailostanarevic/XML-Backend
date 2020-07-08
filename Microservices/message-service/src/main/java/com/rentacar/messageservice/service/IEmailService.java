package com.rentacar.messageservice.service;

import com.rentacar.messageservice.dto.request.MailDTO;

public interface IEmailService {

    void receiveMessage(MailDTO mail);
}
