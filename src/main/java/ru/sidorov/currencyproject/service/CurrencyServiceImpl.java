package ru.sidorov.currencyproject.service;

import org.springframework.stereotype.Service;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.dto.CurrencyResponseDto;
import ru.sidorov.currencyproject.entity.Currency;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.mapper.CurrencyMapper;
import ru.sidorov.currencyproject.repository.CurrencyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
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
        return currencyMapper.toCurrencyDto(currencyRepository.save(currency));
    }
}
