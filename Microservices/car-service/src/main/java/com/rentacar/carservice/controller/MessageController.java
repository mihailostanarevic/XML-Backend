package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.SeenRequest;
import com.rentacar.carservice.dto.request.SendMessageRequest;
import com.rentacar.carservice.dto.response.MessageResponse;
import com.rentacar.carservice.service.IMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final IMessageService _messageService;

    public MessageController(IMessageService messageService) {
        _messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody SendMessageRequest request){
        return _messageService.sendMessage(request);
    }

    @GetMapping
    public List<MessageResponse> getMessages(@RequestParam("receiver") UUID receiverID, @RequestParam("sender") UUID senderID){
        if(senderID == null)
            return _messageService.getAllReceivedMessagesForUser(receiverID);
        else
            return _messageService.getMessagesBetweenUsers(receiverID, senderID);
    }

    @PutMapping("/{id}")
    public void seen(@RequestBody SeenRequest request, @PathVariable("id") UUID id){
        _messageService.seen(request, id);
    }
}
