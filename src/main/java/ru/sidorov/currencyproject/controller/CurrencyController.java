package ru.sidorov.currencyproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.dto.CurrencyResponseDto;
import ru.sidorov.currencyproject.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController implements ICurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public ResponseEntity<CurrencyResponseDto> getByCode(String code) {
        return ResponseEntity.ok(currencyService.getByCode(code));
    }

    @Override
    public ResponseEntity<List<CurrencyResponseDto>> getAll() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @Override
    public ResponseEntity<CurrencyResponseDto> create(CurrencyRequestDto currencyRequestDto) {
        return ResponseEntity.ok(currencyService.createCurrency(currencyRequestDto));
    }
}