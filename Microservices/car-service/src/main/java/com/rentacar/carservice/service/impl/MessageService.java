package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.repository.IMessageRepository;
import com.rentacar.carservice.dto.request.SendMessageRequest;
import com.rentacar.carservice.dto.response.MessageResponse;
import com.rentacar.carservice.service.IMessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService implements IMessageService {

    private final IMessageRepository _messageRepository;

    public MessageService(IMessageRepository messageRepository) {
        _messageRepository = messageRepository;
    }

    @Override
    public MessageResponse sendMessage(SendMessageRequest request) throws Exception {
        return null;
    }

    @Override
    public void deleteMessage(UUID id) throws Exception {

    }

    @Override
    public MessageResponse getMessage(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<MessageResponse> getAllMessages() throws Exception {
        return null;
    }

    @Override
    public List<MessageResponse> getAllMessagesByUser(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<MessageResponse> getAllMessagesBySender(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<MessageResponse> getAllMessagesByRecipient(UUID id) throws Exception {
        return null;
    }
}
