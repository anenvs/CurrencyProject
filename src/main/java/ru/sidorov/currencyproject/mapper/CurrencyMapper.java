package ru.sidorov.currencyproject.mapper;

import org.springframework.stereotype.Component;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.dto.CurrencyResponseDto;
import ru.sidorov.currencyproject.entity.Currency;

@Component
public class CurrencyMapper {
    public CurrencyResponseDto toCurrencyDto(Currency currency) {
        return new CurrencyResponseDto(currency.getCode(), currency.getName(), currency.getCode() + " - " + currency.getName());
    }

    public Currency toCurrency(CurrencyRequestDto dto) {
        return new Currency(dto.getCode(),dto.getName());
    }
}
