package ru.sidorov.currencyproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponseDto {
    @Schema(
            title = "Код валюты",
            description = "Трибуквенный ISO-код валюты (например: USD)",
            example = "USD"
    )
    private String code;

    @Schema(
            title = "Название валюты",
            description = "Полное название валюты (например: Доллар США)",
            example = "Доллар США"
    )
    private String name;

    @Schema(
            title = "Описание валюты",
            description = "Дополнительное описание валюты (опционально)",
            example = "Официальная валюта Соединенных Штатов Америки."
    )
    private String description;

}
