package com.course.controller;

import com.course.model.ConvertResponse;
import com.course.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/currency-converter")
public class CurrencyConverterController {

    @Autowired
    private CurrencyConverterService converterService;

    @GetMapping("/exchange/from/{from}/to/{to}/amount/{amount}")
    public ConvertResponse getStringFromConfigServer(@PathVariable String from, @PathVariable String to,
                                                     @PathVariable String amount) {
//        return converterService.convert("USD", "UAH", new BigDecimal("100.13"));
        return converterService.convert(from, to, new BigDecimal(amount));
    }

}
