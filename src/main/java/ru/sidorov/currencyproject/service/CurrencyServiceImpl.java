package ru.sidorov.currencyproject.service;

import org.springframework.stereotype.Service;
import ru.sidorov.currencyproject.config.metrics.CounterMetrics;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.dto.CurrencyResponseDto;
import ru.sidorov.currencyproject.entity.Currency;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.kafka.producer.CurrencyProducer;
import ru.sidorov.currencyproject.mapper.CurrencyMapper;
import ru.sidorov.currencyproject.mapper.CurrencyMapperV2;
import ru.sidorov.currencyproject.repository.CurrencyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapperV2 currencyMapper;
    private final CurrencyProducer currencyProducer;
    private final CounterMetrics counterMetrics;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyMapperV2 currencyMapper, CurrencyProducer currencyProducer, CounterMetrics counterMetrics) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
        this.currencyProducer = currencyProducer;
        this.counterMetrics = counterMetrics;
    }

    @Override
    public CurrencyResponseDto getByCode(String code) {
        return currencyRepository.findById(code).map(currencyMapper::toCurrencyDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Currency with code %s not found", code)));
    }

    @Override
    public List<CurrencyResponseDto> getAllCurrencies() {
        return currencyRepository.findAll().stream()
                .map(currencyMapper::toCurrencyDto)
                .collect(Collectors.toList());
    }

    @Override
    public CurrencyResponseDto createCurrency(CurrencyRequestDto currencyRequestDto) {
        Currency currency = currencyMapper.toCurrency(currencyRequestDto);
        CurrencyResponseDto currencyResponseDto = currencyMapper.toCurrencyDto(currencyRepository.save(currency));
        counterMetrics.incrementCurrencyCreation();
        return currencyResponseDto;
    }
}
