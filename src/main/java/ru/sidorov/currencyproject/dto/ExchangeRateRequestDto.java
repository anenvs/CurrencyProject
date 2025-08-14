package ru.sidorov.currencyproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateRequestDto {
    @Schema(
            title = "Код валюты",
            description = "ISO-код валюты, для которой запрашивается курс",
            example = "USD",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("currency_code")
    @NotBlank(message = "currencyCode shouldn't be empty")
    private String currencyCode;

    @Schema(
            title = "Обменный курс",
            description = "Актуальный обменный курс валюты",
            example = "0.85",
            minimum = "0.0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @DecimalMin(value = "0.0", message = "rate should be greater than 0")
    private BigDecimal rate;

}
