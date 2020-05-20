package com.rentacar.messageservice.service;

import com.rentacar.messageservice.dto.request.SendMessageRequest;
import com.rentacar.messageservice.dto.response.MessageResponse;

import java.util.List;
import java.util.UUID;

public interface IMessageService {

    MessageResponse sendMessage(SendMessageRequest request) throws Exception;

    void deleteMessage(UUID id) throws Exception;

    MessageResponse getMessage(UUID id) throws Exception;

    List<MessageResponse> getAllMessages() throws Exception;

    List<MessageResponse> getAllMessagesByUser(UUID id) throws Exception;

    List<MessageResponse> getAllMessagesBySender(UUID id) throws Exception;

    List<MessageResponse> getAllMessagesByRecipient(UUID id) throws Exception;
}
