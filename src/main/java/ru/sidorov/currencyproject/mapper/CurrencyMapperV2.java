package ru.sidorov.currencyproject.mapper;

import org.mapstruct.*;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.dto.CurrencyResponseDto;
import ru.sidorov.currencyproject.entity.Currency;

import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        imports = LocalDateTime.class
)
public abstract class CurrencyMapperV2 {

    @Mapping(target = "description", expression = "java(currency.getCode() + \" - \" + currency.getName())")
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    public abstract CurrencyResponseDto toCurrencyDto(Currency currency);

    public abstract Currency toCurrency(CurrencyRequestDto dto);

    @Named("formatDescription")
    protected String formatDescription(String code, String name) {
        return code + " - " + name;
    }
}