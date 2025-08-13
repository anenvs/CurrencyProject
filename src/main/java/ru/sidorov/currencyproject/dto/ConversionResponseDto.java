package ru.sidorov.currencyproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

public class ConversionResponseDto {
    private UUID id;
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private BigDecimal amount;
    @JsonProperty("converted_amount")
    private BigDecimal convertedAmount;
    private BigDecimal rate;
}
