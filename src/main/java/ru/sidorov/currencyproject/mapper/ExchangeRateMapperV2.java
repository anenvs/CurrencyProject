package ru.sidorov.currencyproject.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sidorov.currencyproject.dto.ExchangeRateRequestDto;
import ru.sidorov.currencyproject.dto.ExchangeRateResponseDto;
import ru.sidorov.currencyproject.entity.Currency;
import ru.sidorov.currencyproject.entity.ExchangeRate;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.repository.CurrencyRepository;

import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        imports = LocalDateTime.class
)
public abstract class ExchangeRateMapperV2 {

    @Autowired
    protected CurrencyRepository currencyRepository;

    @Mapping(target = "rate", source = "rate")
    @Mapping(target = "date", ignore = true)
    public abstract ExchangeRate toExchangeRate(ExchangeRateRequestDto exchangeRateRequestDto);

    @Mapping(target = "currencyCode", source = "currency.code")
    @Mapping(target = "rate", source = "rate")
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    public abstract ExchangeRateResponseDto toExchangeRateResponseDto(ExchangeRate exchangeRate);

    @AfterMapping
    protected void setCurrencyAndDate(ExchangeRateRequestDto dto, @MappingTarget ExchangeRate exchangeRate) {
        Currency currency = currencyRepository.findById(dto.getCurrencyCode())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Currency with code %s not found", dto.getCurrencyCode())
                ));
        exchangeRate.setCurrency(currency);
        exchangeRate.setDate(LocalDateTime.now());
    }
}