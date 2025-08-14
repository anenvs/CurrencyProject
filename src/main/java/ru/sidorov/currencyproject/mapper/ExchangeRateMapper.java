package ru.sidorov.currencyproject.mapper;

import org.springframework.stereotype.Component;
import ru.sidorov.currencyproject.dto.ExchangeRateRequestDto;
import ru.sidorov.currencyproject.dto.ExchangeRateResponseDto;
import ru.sidorov.currencyproject.entity.Currency;
import ru.sidorov.currencyproject.entity.ExchangeRate;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.repository.CurrencyRepository;

import java.time.LocalDateTime;

@Component
public class ExchangeRateMapper {
    private final CurrencyRepository currencyRepository;

    public ExchangeRateMapper(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public ExchangeRate toExchangeRate(ExchangeRateRequestDto exchangeRateRequestDto) {
        ExchangeRate exchangeRate = new ExchangeRate();
        Currency Currency = currencyRepository.findById(exchangeRateRequestDto.getCurrencyCode()).orElseThrow(() -> new EntityNotFoundException(String.format("fromCurrency with code %s not found", exchangeRateRequestDto.getCurrencyCode())));
        exchangeRate.setCurrency(Currency);
        exchangeRate.setRate(exchangeRateRequestDto.getRate());
        exchangeRate.setDate(LocalDateTime.now());
        return exchangeRate;
    }

    public ExchangeRateResponseDto toExchangeRateResponseDto(ExchangeRate exchangeRate) {
        return new ExchangeRateResponseDto(
                exchangeRate.getCurrency().getName(),
                exchangeRate.getRate()
        );
    }
}
