package com.rentacar.authentificationservice.services;

import com.rentacar.authentificationservice.entity.Agent;
import com.rentacar.authentificationservice.entity.SimpleUser;

public interface IEmailService {

    void approveRegistrationMail(SimpleUser simpleUser);

    void agentRegistrationMail(Agent agent);
}
