package ru.sidorov.currencyproject.mapper;

import org.mapstruct.*;
import ru.sidorov.currencyproject.dto.ConversionRequestDto;
import ru.sidorov.currencyproject.dto.ConversionResponseDto;
import ru.sidorov.currencyproject.entity.Conversion;

import java.math.BigDecimal;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS //Проверка на Null
)
public abstract class ConversionMapperV2 {

    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "rate", source = "rate", qualifiedByName = "ToRate")
    public abstract Conversion toConversion(ConversionRequestDto requestDto);

    @Named("ToRate")
    protected BigDecimal toRate(BigDecimal sourceRate) {
        return sourceRate.add(BigDecimal.valueOf(100));
    }

    @Mapping(target = "fromCurrencyCode", source = "fromCurrency.code")
    @Mapping(target = "toCurrencyCode", source = "toCurrency.code")
    public abstract ConversionResponseDto toConversionResponse(Conversion conversion);

}
