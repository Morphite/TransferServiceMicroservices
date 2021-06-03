package com.course.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency-converter")
public class CurrencyConverterController {

    @Value("${my.property: default value}")
    private String fromConfig;

    @GetMapping
    public String getStringFromConfigServer() {
        return "hello, it's your response: " + fromConfig;
    }

}
