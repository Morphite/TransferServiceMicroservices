package com.course.service;

import com.course.model.ConvertResponse;

import java.math.BigDecimal;

public interface CurrencyConverterService {

    ConvertResponse convert(String from, String to, BigDecimal amount);
}