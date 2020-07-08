package com.rentacar.messageservice.consumer;

import com.rentacar.messageservice.service.impl.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    EmailService emailService;

    public void receiveMessage(String message) {
        processMessage(message);
    }

    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);
        processMessage(strMessage);
    }
    private void processMessage(String message) {
        boolean sent = false;
        try {
            SimpleUserDTO simpleUser = new ObjectMapper().readValue(message, SimpleUserDTO.class);
            emailService.approveRegistrationMail(simpleUser);
            sent = true;
        } catch(Exception e) {
            System.out.println("This is not a simple user mail");
        }

        if(!sent){
            try {
                AgentDTO agent = new ObjectMapper().readValue(message, AgentDTO.class);
                emailService.agentRegistrationMail(agent);
                sent = true;
            } catch(Exception e) {
                System.out.println("This is not a agent user mail");
            }
        }
    }
}
