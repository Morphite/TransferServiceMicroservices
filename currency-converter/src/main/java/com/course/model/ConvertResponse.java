package com.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@Data
public class ConvertResponse {

    private String baseCurrencyCode;
    private String baseCurrencyName;
    private BigDecimal amount;
    private String updatedDate;
    private Map rates;
    private String status;
}
