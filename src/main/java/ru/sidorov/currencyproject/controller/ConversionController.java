package ru.sidorov.currencyproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.currencyproject.dto.ConversionRequestDto;
import ru.sidorov.currencyproject.entity.Conversion;
import ru.sidorov.currencyproject.service.ConversionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/conversion")
public class ConversionController {
    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/all")
    ResponseEntity<List<Conversion>> getAll() {
        return ResponseEntity.ok(conversionService.getAllConversions());
    }

    @PostMapping("/create")
    ResponseEntity<Conversion> create(@RequestBody ConversionRequestDto conversionRequestDto) {
        return ResponseEntity.ok(conversionService.create(conversionRequestDto));
    }
}
