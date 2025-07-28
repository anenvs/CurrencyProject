package ru.sidorov.currencyproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.currencyproject.entity.ExchangeRate;
import ru.sidorov.currencyproject.service.ExchangeRateService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/exchangerate")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/{id}")
    ResponseEntity<ExchangeRate> getById(@RequestParam UUID id) {
        return ResponseEntity.ok(exchangeRateService.getById(id));
    }

    @GetMapping("/all")
    ResponseEntity<List<ExchangeRate>> getAll() {
        return ResponseEntity.ok(exchangeRateService.getAll());
    }
}
