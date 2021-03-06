package com.course.service.impl;

import com.course.model.ConvertResponse;
import com.course.service.CurrencyConverterService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    private CurrencyConverterFeignClient feignClient;

    public CurrencyConverterServiceImpl(CurrencyConverterFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public ConvertResponse convert(String from, String to, BigDecimal amount) {
        return feignClient.convertCurrency(from, to, amount.doubleValue());
    }

    @FeignClient(value = "currency", url = "https://api.getgeoapi.com/v2")
    interface CurrencyConverterFeignClient {

        String apiKey = "8871a309543e95c83116bb8723b76968092921bf";

        @GetMapping("/currency/convert?api_key=" + apiKey + "&from={from}&to={to}&amount={amount}")
        ConvertResponse convertCurrency(@PathVariable String from, @PathVariable String to,
                                        @PathVariable Double amount);
    }
}
