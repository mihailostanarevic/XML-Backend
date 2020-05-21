package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.service.implementation.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@RestController
@RequestMapping("/pricelist")
public class PricelistController {

    @Autowired
    PricelistService _pricelistService;

}
