package ru.sidorov.currencyproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sidorov.currencyproject.entity.Currency;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.repository.CurrencyRepository;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency getByCode(String code) {
        return currencyRepository.findById(code).orElseThrow(() -> new EntityNotFoundException(String.format("Currency with code %s not found", code)));
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
}
