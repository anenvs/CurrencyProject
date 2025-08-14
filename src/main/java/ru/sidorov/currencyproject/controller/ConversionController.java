package ru.sidorov.currencyproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.currencyproject.dto.ConversionRequestDto;
import ru.sidorov.currencyproject.dto.ConversionResponseDto;
import ru.sidorov.currencyproject.service.ConversionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/conversion")
public class ConversionController implements IConversionController {
    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ResponseEntity<List<ConversionResponseDto>> getAll() {
        return ResponseEntity.ok(conversionService.getAllConversions());
    }

    @Override
    public ResponseEntity<ConversionResponseDto> create(ConversionRequestDto conversionRequestDto) {
        return ResponseEntity.ok(conversionService.create(conversionRequestDto));
    }

}
