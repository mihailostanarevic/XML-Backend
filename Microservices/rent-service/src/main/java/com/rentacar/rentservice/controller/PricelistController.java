package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.service.IPricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@RestController
@RequestMapping("/rent/pricelist")
public class PricelistController {

    @Autowired
    IPricelistService _pricelistService;

}
