package com.example.demo.controllers;

import com.example.demo.models.Conversion;
import com.example.demo.models.ConversionRepository;
import com.example.demo.models.Currency;
import com.example.demo.models.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    ConversionRepository conversionRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @GetMapping("/conversion")
    public String createCurrencyPair(@RequestBody Map<String, String> body) {
        String firstCurrencyName = body.getOrDefault("firstCurrency", "");
        String secondCurrencyName = body.getOrDefault("secondCurrency", "");
        float amount = Float.parseFloat(body.getOrDefault("amount", "0.0"));

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
