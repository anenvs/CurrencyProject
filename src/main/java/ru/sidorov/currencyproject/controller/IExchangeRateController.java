package ru.sidorov.currencyproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.currencyproject.dto.ExchangeRateRequestDto;
import ru.sidorov.currencyproject.dto.ExchangeRateResponseDto;

import java.util.List;
import java.util.UUID;

@Tag(name = "Exchange Rate API", description = "API для управления обменными курсами валют.")
public interface IExchangeRateController {

    @Operation(
            summary = "Получить курс обмена по уникальному идентификатору",
            description = "Возвращает обменный курс для заданного идентификатора.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Курсы обмена найдены",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExchangeRateResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Курс обмена не найден",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class))
                    )
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<ExchangeRateResponseDto> getById(@PathVariable UUID id);

    @Operation(
            summary = "Получить все курсы обмена",
            description = "Возвращает список всех зарегистрированных курсов обмена валют.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список курсов обмена успешно получен",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "array", implementation = ExchangeRateResponseDto.class))
                    )
            }
    )
    @GetMapping("/all")
    ResponseEntity<List<ExchangeRateResponseDto>> getAll();

    @Operation(
            summary = "Создать новый курс обмена",
            description = "Регистрирует новый курс обмена валют и возвращает созданный объект.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новый курс обмена успешно создан",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExchangeRateResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Некорректные входные данные",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExchangeRateRequestDto.class))
                    )
            }
    )
    @PostMapping("/create")
    ResponseEntity<ExchangeRateResponseDto> create(
            @Parameter(description = "Информация о новом курсе обмена.", required = true)
            @RequestBody ExchangeRateRequestDto exchangeRateRequestDto
    );
}