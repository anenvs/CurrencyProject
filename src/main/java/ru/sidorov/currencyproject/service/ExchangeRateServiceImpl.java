package ru.sidorov.currencyproject.service;

import org.springframework.stereotype.Service;
import ru.sidorov.currencyproject.dto.ExchangeRateRequestDto;
import ru.sidorov.currencyproject.entity.ExchangeRate;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.mapper.ExchangeRateMapper;
import ru.sidorov.currencyproject.repository.ExchangeRateRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMapper exchangeRateMapper;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository, ExchangeRateMapper exchangeRateMapper) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateMapper = exchangeRateMapper;
    }

    @Override
    public ExchangeRate getById(UUID id) {
        return exchangeRateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Exchange rate with id %s not found", id)));
    }

    @Override
    public List<ExchangeRate> getAll() {
        return exchangeRateRepository.findAll();
    }

    @Override
    public ExchangeRate create(ExchangeRateRequestDto exchangeRateRequestDto) {
        ExchangeRate exchangeRate = exchangeRateMapper.toExchangeRate(exchangeRateRequestDto);
        return exchangeRateRepository.save(exchangeRate);
    }
}
