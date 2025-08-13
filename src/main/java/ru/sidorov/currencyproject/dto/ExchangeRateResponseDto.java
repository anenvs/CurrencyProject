package ru.sidorov.currencyproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponseDto {
    @JsonProperty("currency_code")
    private String currencyCode;
    private BigDecimal rate;

    public ExchangeRateResponseDto(String currencyCode, BigDecimal rate) {
        this.currencyCode = currencyCode;
        this.rate = rate;
    }


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
