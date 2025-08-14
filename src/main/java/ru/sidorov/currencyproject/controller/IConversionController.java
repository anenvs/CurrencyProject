package ru.sidorov.currencyproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.sidorov.currencyproject.dto.ConversionRequestDto;
import ru.sidorov.currencyproject.dto.ConversionResponseDto;

import java.util.List;

@Tag(name = "Conversion API", description = "API для управления операциями конвертации валют.")
public interface IConversionController {
    @Operation(
            summary = "Получить все истории конвертации",
            description = "Возвращает список всех выполненных операций конвертации валют.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Запрошенные данные успешно получены",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "array", implementation = ConversionResponseDto.class))
                    )
            }
    )
    @GetMapping("/all")
    ResponseEntity<List<ConversionResponseDto>> getAll();

    @Operation(
            summary = "Создать новую историю конвертации",
            description = "Выполняет новую операцию конвертации валют и сохраняет результат.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новая операция успешно создана",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ConversionResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Некорректные входные данные",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ConversionRequestDto.class))
                    )
            }
    )
    @PostMapping("/create")
    ResponseEntity<ConversionResponseDto> create(
            @Parameter(description = "Параметры новой операции конвертации.", required = true)
            @RequestBody @Validated ConversionRequestDto conversionRequestDto
    );
}
