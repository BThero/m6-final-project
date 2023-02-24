package com.example.demo.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConversionRepository extends JpaRepository<Conversion, Long> {
    Optional<Conversion> findConversionByFirstCurrencyAndSecondCurrency(Currency firstCurrency, Currency secondCurrency);
}
