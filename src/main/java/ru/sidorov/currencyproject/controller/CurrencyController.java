package ru.sidorov.currencyproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.dto.CurrencyResponseDto;
import ru.sidorov.currencyproject.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{code}")
    ResponseEntity<CurrencyResponseDto> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(currencyService.getByCode(code));
    }

    @GetMapping("/all")
    ResponseEntity<List<CurrencyResponseDto>> getAll() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @PostMapping("/create")
    ResponseEntity<CurrencyResponseDto> create(@RequestBody CurrencyRequestDto currencyRequestDto) {
        return ResponseEntity.ok(currencyService.createCurrency(currencyRequestDto));
    }
}
