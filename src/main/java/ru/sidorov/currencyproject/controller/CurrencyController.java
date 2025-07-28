package ru.sidorov.currencyproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.currencyproject.entity.Currency;
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
    ResponseEntity<Currency> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(currencyService.getByCode(code));
    }

    @GetMapping("/all")
    ResponseEntity<List<Currency>> getAll() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }
}
