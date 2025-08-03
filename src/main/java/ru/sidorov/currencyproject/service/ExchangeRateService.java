package ru.sidorov.currencyproject.service;

import ru.sidorov.currencyproject.dto.ExchangeRateRequestDto;
import ru.sidorov.currencyproject.entity.ExchangeRate;

import java.util.List;
import java.util.UUID;

public interface ExchangeRateService {
    ExchangeRate getById(UUID id);
    List<ExchangeRate> getAll();
    ExchangeRate create(ExchangeRateRequestDto exchangeRateRequestDto);
}
