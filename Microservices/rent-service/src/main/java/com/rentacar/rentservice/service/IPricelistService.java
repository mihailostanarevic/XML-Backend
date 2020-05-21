package com.rentacar.rentservice.service;

import com.rentacar.rentservice.entity.Pricelist;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpellCheckingInspection")
@Service
public interface IPricelistService {

    Pricelist addPricelist();       // params

    Pricelist editPricelist();      // params

    void deletePricelist();         // params

}
