package ru.sidorov.currencyproject.service;

import ru.sidorov.currencyproject.dto.ConversionRequestDto;
import ru.sidorov.currencyproject.dto.ConversionResponseDto;

import java.util.List;
import java.util.UUID;

public interface ConversionService {
    ConversionResponseDto getById(UUID id);

    List<ConversionResponseDto> getAllConversions();

    ConversionResponseDto update(ConversionRequestDto conversionRequestDto);

    ConversionResponseDto create(ConversionRequestDto conversionRequestDto);
}
