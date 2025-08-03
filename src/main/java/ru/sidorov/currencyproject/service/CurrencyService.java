package ru.sidorov.currencyproject.service;

import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.dto.CurrencyResponseDto;

import java.util.List;

public interface CurrencyService {
   CurrencyResponseDto getByCode(String code);
   List<CurrencyResponseDto> getAllCurrencies();
   CurrencyResponseDto createCurrency(CurrencyRequestDto currencyRequestDto);
}
