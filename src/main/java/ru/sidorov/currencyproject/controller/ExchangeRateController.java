package ru.sidorov.currencyproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.currencyproject.dto.ExchangeRateRequestDto;
import ru.sidorov.currencyproject.dto.ExchangeRateResponseDto;
import ru.sidorov.currencyproject.service.ExchangeRateService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/exchangerate")
public class ExchangeRateController implements IExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Override
    public ResponseEntity<ExchangeRateResponseDto> getById(UUID id) {
        return ResponseEntity.ok(exchangeRateService.getById(id));
    }

    @Override
    public ResponseEntity<List<ExchangeRateResponseDto>> getAll() {
        return ResponseEntity.ok(exchangeRateService.getAll());
    }

    @Override
    public ResponseEntity<ExchangeRateResponseDto> create(ExchangeRateRequestDto exchangeRateRequestDto) {
        return ResponseEntity.ok(exchangeRateService.create(exchangeRateRequestDto));
    }
}
