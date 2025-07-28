package ru.sidorov.currencyproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.currencyproject.entity.Conversion;
import ru.sidorov.currencyproject.entity.Currency;
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
}
