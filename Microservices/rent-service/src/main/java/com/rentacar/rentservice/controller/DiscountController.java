package com.rentacar.rentservice.controller;

import com.rentacar.rentservice.service.IDiscountService;
import com.rentacar.rentservice.service.implementation.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    @Autowired
    IDiscountService _discountService;

}
