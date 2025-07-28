package ru.sidorov.currencyproject.service;

import ru.sidorov.currencyproject.entity.Currency;

import java.util.List;

public interface CurrencyService {
   Currency getByCode(String code);
   List<Currency> getAllCurrencies();
}
