package com.course.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
public class ConvertResponse {

    @JsonProperty("base_currency_code")
    private String baseCurrencyCode;
    @JsonProperty("base_currency_name")
    private String baseCurrencyName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;
    @JsonProperty("updated_date")
    private String updatedDate;
    private Map rates;
    private String status;
}

