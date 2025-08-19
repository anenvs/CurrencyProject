package ru.sidorov.currencyproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@Builder
public class ConversionResponseDto {
    @Schema(
            title = "Идентификатор записи",
            description = "Уникальный идентификатор транзакции",
            example = "123e4567-e89b-12d3-a456-426655440000"
    )
    private UUID id;

    @Schema(
            title = "Исходная валюта",
            description = "Код исходной валюты (например: USD)",
            example = "USD"
    )
    private String fromCurrencyCode;

    @Schema(
            title = "Конечная валюта",
            description = "Код целевой валюты (например: EUR)",
            example = "EUR"
    )
    private String toCurrencyCode;

    @Schema(
            title = "Сумма конвертации",
            description = "Значение суммы, подлежащей конвертации",
            example = "100.00"
    )
    private BigDecimal amount;

    @Schema(
            title = "Результат конвертации",
            description = "Результирующая сумма после конвертации",
            example = "85.00"
    )
    @JsonProperty("converted_amount")
    private BigDecimal convertedAmount;

    @Schema(
            title = "Курс обмена",
            description = "Применённый курс обмена",
            example = "0.85"
    )
    private BigDecimal rate;
}
