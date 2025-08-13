package ru.sidorov.currencyproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversionRequestDto {
    @NotBlank(message = "fromCurrencyCode shouldn't be empty")
    private String fromCurrencyCode;
    @NotBlank(message = "toCurrencyCode shouldn't be empty")
    private String toCurrencyCode;
    @DecimalMin(value = "0.0", message = "Amount should be greater then 0")
    private BigDecimal amount;
    @JsonProperty("converted_amount")
    @DecimalMin(value = "0.0", message = "convertedAmount should be greater then 0")
    private BigDecimal convertedAmount;
    @NotNull
    private BigDecimal rate;

    public ConversionRequestDto(String fromCurrencyCode, String toCurrencyCode, BigDecimal amount, BigDecimal convertedAmount, BigDecimal rate) {
        this.fromCurrencyCode = fromCurrencyCode;
        this.toCurrencyCode = toCurrencyCode;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
        this.rate = rate;
    }

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
