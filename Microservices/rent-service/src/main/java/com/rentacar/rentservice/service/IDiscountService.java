package com.rentacar.rentservice.service;

import com.rentacar.rentservice.entity.Discount;
import com.rentacar.rentservice.entity.Pricelist;
import org.springframework.stereotype.Service;

public interface IDiscountService {

    Discount addDiscount();     // params

    Discount editDiscount();    // params

    void deleteDiscount();      // params

}
