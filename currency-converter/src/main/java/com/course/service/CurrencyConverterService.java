package com.course.service;

import com.course.model.Currency;

import java.math.BigDecimal;

public interface CurrencyConverterService {

    BigDecimal convert(String from, String to, Double amount);
}