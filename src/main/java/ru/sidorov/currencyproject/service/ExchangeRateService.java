package ru.sidorov.currencyproject.service;

import ru.sidorov.currencyproject.dto.ExchangeRateRequestDto;
import ru.sidorov.currencyproject.dto.ExchangeRateResponseDto;

import java.util.List;
import java.util.UUID;

public interface ExchangeRateService {
    ExchangeRateResponseDto getById(UUID id);

    List<ExchangeRateResponseDto> getAll();

    ExchangeRateResponseDto create(ExchangeRateRequestDto exchangeRateRequestDto);
}
