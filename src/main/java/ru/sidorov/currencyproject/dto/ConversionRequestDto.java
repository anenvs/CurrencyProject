package ru.sidorov.currencyproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversionRequestDto {

    @Schema(
            title = "Исходная валюта",
            description = "Код исходной валюты (например: USD)",
            example = "USD",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "fromCurrencyCode shouldn't be empty")
    private String fromCurrencyCode;

    @Schema(
            title = "Конечная валюта",
            description = "Код целевой валюты (например: EUR)",
            example = "EUR",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "toCurrencyCode shouldn't be empty")
    private String toCurrencyCode;

    @Schema(
            title = "Сумма перевода",
            description = "Начальная сумма для конвертации",
            example = "100.00",
            minimum = "0.0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @DecimalMin(value = "0.0", message = "Amount should be greater than 0")
    private BigDecimal amount;

    @Schema(
            title = "Конвертированная сумма",
            description = "Итоговая сумма после конвертации",
            example = "85.00",
            minimum = "0.0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("converted_amount")
    @DecimalMin(value = "0.0", message = "convertedAmount should be greater than 0")
    private BigDecimal convertedAmount;

    @Schema(
            title = "Курс обмена",
            description = "Курс обмена между двумя валютами",
            example = "0.85",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull
    private BigDecimal rate;

}
