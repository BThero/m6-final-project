package com.example.demo.controllers;

import com.example.demo.models.Conversion;
import com.example.demo.models.ConversionRepository;
import com.example.demo.models.Currency;
import com.example.demo.models.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ManagementController {
    @Autowired
    ConversionRepository conversionRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    /**
     * Creates a new currency pair with a specified conversion rate
     * @param body request body. Should contain firstCurrency, secondCurrency and rate fields
     * @return Success/failure message
     */
    @PostMapping("/currencies")
    public String createCurrencyPair(@RequestBody Map<String, String> body) {
        String firstCurrencyName = body.getOrDefault("firstCurrency", "");
        String secondCurrencyName = body.getOrDefault("secondCurrency", "");
        float rate = Float.parseFloat(body.getOrDefault("rate", "1.0"));

        Currency firstCurrency = currencyRepository.findByNameIgnoringCase(firstCurrencyName);
        Currency secondCurrency = currencyRepository.findByNameIgnoringCase(secondCurrencyName);

        if (firstCurrency == null || secondCurrency == null) {
            return "Currencies not found";
        }

        Conversion conversion = conversionRepository.findConversionByFirstCurrencyAndSecondCurrency(firstCurrency, secondCurrency).orElse(null);

        if (conversion != null) {
            return "Conversion already exists";
        }

        conversion = new Conversion(firstCurrency, secondCurrency, rate);
        conversionRepository.save(conversion);
        return "Successfully created";
    }

    /**
     * Updates an existing currency pair with a specified conversion rate
     * @param body request body. Should contain firstCurrency, secondCurrency and rate fields
     * @return Success/failure message
     */
    @PutMapping("/currencies")
    public String updateCurrencyPair(@RequestBody Map<String, String> body) {
        String firstCurrencyName = body.getOrDefault("firstCurrency", "");
        String secondCurrencyName = body.getOrDefault("secondCurrency", "");
        float rate = Float.parseFloat(body.getOrDefault("rate", "1.0"));

        Currency firstCurrency = currencyRepository.findByNameIgnoringCase(firstCurrencyName);
        Currency secondCurrency = currencyRepository.findByNameIgnoringCase(secondCurrencyName);

        if (firstCurrency == null || secondCurrency == null) {
            return "Currencies not found";
        }

        Conversion conversion = conversionRepository.findConversionByFirstCurrencyAndSecondCurrency(firstCurrency, secondCurrency).orElse(null);

        if (conversion == null) {
            return "Conversion does not exist";
        }

        conversion.setRate(rate);
        conversionRepository.save(conversion);
        return "Successfully updated";
    }

    /**
     * Deletes an existing currency pair
     * @param body request body. Should contain firstCurrency, secondCurrency
     * @return Success/failure message
     */
    @DeleteMapping("/currencies")
    public String deleteCurrencyPair(@RequestBody Map<String, String> body) {
        String firstCurrencyName = body.getOrDefault("firstCurrency", "");
        String secondCurrencyName = body.getOrDefault("secondCurrency", "");
        Currency firstCurrency = currencyRepository.findByNameIgnoringCase(firstCurrencyName);
        Currency secondCurrency = currencyRepository.findByNameIgnoringCase(secondCurrencyName);

        Conversion conversion = conversionRepository.findConversionByFirstCurrencyAndSecondCurrency(firstCurrency, secondCurrency).orElse(null);

        if (conversion != null) {
            conversionRepository.delete(conversion);
            return "Successfully deleted";
        }

        return "Conversion not found";
    }
}
