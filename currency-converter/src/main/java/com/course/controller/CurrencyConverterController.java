package com.course.controller;

import com.course.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency-converter")
public class CurrencyConverterController {

    @Value("${my.property: default value}")
    private String fromConfig;

    @Autowired
    private CurrencyConverterService converterService;

    @GetMapping("/convert")
    public Double getStringFromConfigServer() {
        return converterService.convert("USD", "UAH", (double) 100).doubleValue();
    }

}
