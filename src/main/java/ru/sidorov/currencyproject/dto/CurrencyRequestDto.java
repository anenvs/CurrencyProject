package ru.sidorov.currencyproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRequestDto {
    @Schema(
            title = "Код валюты",
            description = "Код валюты",
            example = "RUR"
    )
    @NotBlank(message = "Code shouldn't be empty")
    String code;
    @Schema(
            title = "Описание кода валюты",
            description = "Описание кода валюты",
            example = "russian ruble"
    )
    @NotBlank(message = "Name shouldn't be empty")
    String name;
}
