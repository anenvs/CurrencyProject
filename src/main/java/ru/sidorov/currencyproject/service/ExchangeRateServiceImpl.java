package ru.sidorov.currencyproject.service;

import org.springframework.stereotype.Service;
import ru.sidorov.currencyproject.dto.ExchangeRateRequestDto;
import ru.sidorov.currencyproject.dto.ExchangeRateResponseDto;
import ru.sidorov.currencyproject.entity.ExchangeRate;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.mapper.ExchangeRateMapper;
import ru.sidorov.currencyproject.repository.ExchangeRateRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMapper exchangeRateMapper;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository, ExchangeRateMapper exchangeRateMapper) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateMapper = exchangeRateMapper;
    }

    @Override
    public ExchangeRateResponseDto getById(UUID id) {
        return exchangeRateRepository.findById(id).map(exchangeRateMapper::toExchangeRateResponseDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Exchange rate with id %s not found", id)));
    }

    @Override
    public List<ExchangeRateResponseDto> getAll() {
        return exchangeRateRepository.findAll().stream()
                .map(exchangeRateMapper::toExchangeRateResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExchangeRateResponseDto create(ExchangeRateRequestDto exchangeRateRequestDto) {
        ExchangeRate exchangeRate = exchangeRateMapper.toExchangeRate(exchangeRateRequestDto);
        return exchangeRateMapper.toExchangeRateResponseDto(exchangeRate);
    }
}
