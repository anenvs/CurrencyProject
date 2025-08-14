package ru.sidorov.currencyproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponseDto {
    @Schema(
            title = "Код валюты",
            description = "ISO-код валюты, для которой предоставляется курс",
            example = "USD"
    )
    @JsonProperty("currency_code")
    private String currencyCode;

    @Schema(
            title = "Обменный курс",
            description = "Текущий обменный курс для заданной валюты",
            example = "0.85"
    )
    private BigDecimal rate;
}
