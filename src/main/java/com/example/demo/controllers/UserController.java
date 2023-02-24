package com.example.demo.controllers;

import com.example.demo.models.Conversion;
import com.example.demo.models.ConversionRepository;
import com.example.demo.models.Currency;
import com.example.demo.models.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    ConversionRepository conversionRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    /**
     * Given the currency pair and an amount to convert, returns the converted amount
     * @param firstCurrencyName The first currency's name
     * @param secondCurrencyName The second currency's name
     * @param amount The amount to be converted
     * @return Success/failure message
     */
    @GetMapping("/conversion")
    public String createCurrencyPair(@RequestParam(name = "firstCurrency") String firstCurrencyName, @RequestParam(name = "secondCurrency") String secondCurrencyName, @RequestParam float amount) {
        Currency firstCurrency = currencyRepository.findByNameIgnoringCase(firstCurrencyName);
        Currency secondCurrency = currencyRepository.findByNameIgnoringCase(secondCurrencyName);

        if (firstCurrency == null || secondCurrency == null) {
            return "Currencies not found";
        }

        Conversion conversion = conversionRepository.findConversionByFirstCurrencyAndSecondCurrency(firstCurrency, secondCurrency).orElse(null);

        if (conversion == null) {
            return "Conversion does not exist";
        }

        return String.format("Converted amount is %.3f", amount * conversion.getRate());
    }
}
