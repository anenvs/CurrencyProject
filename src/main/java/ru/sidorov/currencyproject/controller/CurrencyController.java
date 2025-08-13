package ru.sidorov.currencyproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.dto.CurrencyResponseDto;
import ru.sidorov.currencyproject.dto.ErrorDto;
import ru.sidorov.currencyproject.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("api/v1/currency")
@Tag(name = "Currency API", description = "API для работы с валютами")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Operation(
            summary = "Получить валюту по коду",
            description = "Возвращает информацию о валюте по её коду"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Валюта найдена",
                    content = @Content(schema = @Schema(implementation = CurrencyResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Валюта не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))
            )
    })
    @GetMapping("/{code}")
    ResponseEntity<CurrencyResponseDto> getByCode(
            @Parameter(description = "Код валюты (например: USD, EUR)", required = true, example = "USD")
            @PathVariable String code) {
        return ResponseEntity.ok(currencyService.getByCode(code));
    }

    @Operation(
            summary = "Получить все валюты",
            description = "Возвращает список всех доступных валют"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Список валют успешно получен",
            content = @Content(schema = @Schema(implementation = CurrencyResponseDto.class))
    )
    @GetMapping("/all")
    ResponseEntity<List<CurrencyResponseDto>> getAll() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @Operation(
            summary = "Создать новую валюту",
            description = "Создает новую запись о валюте"
    )

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Валюта успешно создана",
                    content = @Content(schema = @Schema(implementation = CurrencyResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверные параметры запроса",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))
            )
    })
    @PostMapping("/create")
    ResponseEntity<CurrencyResponseDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для создания валюты",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CurrencyRequestDto.class)))
            @RequestBody CurrencyRequestDto currencyRequestDto) {
        return ResponseEntity.ok(currencyService.createCurrency(currencyRequestDto));
    }
}