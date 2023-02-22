package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {
    @PostMapping("/currency")
    public String createCurrencyPair(@RequestBody String a, @RequestBody String b, @RequestBody float c) {
        return String.format("Conversion from %s to %s with exchange rate %.3f\n", a, b, c);
    }
}
