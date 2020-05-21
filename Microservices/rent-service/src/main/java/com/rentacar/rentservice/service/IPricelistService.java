package com.rentacar.rentservice.service;

import com.rentacar.rentservice.entity.Pricelist;

@SuppressWarnings("SpellCheckingInspection")
public interface IPricelistService {

    Pricelist addPricelist();       // params

    Pricelist editPricelist();      // params

    void deletePricelist();         // params

}
