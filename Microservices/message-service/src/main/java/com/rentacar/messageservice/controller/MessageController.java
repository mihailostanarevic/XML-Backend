package com.rentacar.messageservice.controller;

import com.rentacar.messageservice.dto.request.SendEmailRequest;
import com.rentacar.messageservice.dto.response.EmailSentResponse;
import com.rentacar.messageservice.service.IMessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final IMessageService _messageService;

    public MessageController(IMessageService messageService) {
        _messageService = messageService;
    }

    @PostMapping
    public EmailSentResponse sendEmail(@RequestBody SendEmailRequest request) throws Exception{
        return this._messageService.sendMessage(request);
    }
}
