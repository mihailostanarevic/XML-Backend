package com.rentacar.authentificationservice.services.implementation;

import com.rentacar.authentificationservice.config.EmailContext;
import com.rentacar.authentificationservice.entity.Agent;
import com.rentacar.authentificationservice.entity.SimpleUser;
import com.rentacar.authentificationservice.services.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService {

    private final EmailContext _emailContext;

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public EmailService(EmailContext emailContext) {
        _emailContext = emailContext;
    }

    @Override
    public void approveRegistrationMail(SimpleUser simpleUser) {
        String to = simpleUser.getUser().getUsername();
        String subject = "Registration announcement";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", simpleUser.getFirstName(), simpleUser.getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/auth/login/%s/simple-user", simpleUser.getId()));
        _emailContext.send(to, subject, "approveRegistration", context);
        logger.info("Email sent to " + to + " about a pending registration");
    }

    @Override
    public void agentRegistrationMail(Agent agent) {
        String to = agent.getUser().getUsername();
        String subject = "Registration announcement";
        Context context = new Context();
        context.setVariable("name", String.format("%s", agent.getName()));
        _emailContext.send(to, subject, "agentRegistration", context);
        logger.info("Email sent to " + to + " about his registration being approved");
    }
}
