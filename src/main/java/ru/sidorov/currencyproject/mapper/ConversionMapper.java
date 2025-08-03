package ru.sidorov.currencyproject.mapper;

import org.springframework.stereotype.Component;
import ru.sidorov.currencyproject.dto.ConversionRequestDto;
import ru.sidorov.currencyproject.entity.Conversion;
import ru.sidorov.currencyproject.entity.Currency;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.repository.CurrencyRepository;

import java.time.LocalDateTime;

@Component
public class ConversionMapper {
    private final CurrencyRepository currencyRepository;

    public ConversionMapper(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Conversion toConversion(ConversionRequestDto requestDto) {
        Conversion conversion = new Conversion();
        Currency fromCurrency = currencyRepository.findById(requestDto.getFromCurrencyCode()).orElseThrow(() -> new EntityNotFoundException(String.format("fromCurrency with code %s not found", requestDto.getFromCurrencyCode())));
        Currency toCurrency = currencyRepository.findById(requestDto.getToCurrencyCode()).orElseThrow(() -> new EntityNotFoundException(String.format("toCurrency with code %s not found", requestDto.getToCurrencyCode())));
        conversion.setFromCurrency(fromCurrency);
        conversion.setToCurrency(toCurrency);
        conversion.setAmount(requestDto.getAmount());
        conversion.setConvertedAmount(requestDto.getConvertedAmount());
        conversion.setRate(requestDto.getRate());
        conversion.setDate(LocalDateTime.now());
        return conversion;
    }
}
