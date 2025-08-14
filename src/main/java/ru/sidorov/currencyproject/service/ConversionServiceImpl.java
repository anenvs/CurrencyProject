package ru.sidorov.currencyproject.service;

import org.springframework.stereotype.Service;
import ru.sidorov.currencyproject.dto.ConversionRequestDto;
import ru.sidorov.currencyproject.dto.ConversionResponseDto;
import ru.sidorov.currencyproject.entity.Conversion;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.mapper.ConversionMapper;
import ru.sidorov.currencyproject.repository.ConversionRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConversionServiceImpl implements ConversionService {
    private final ConversionRepository conversionRepository;
    private final ConversionMapper conversionMapper;

    public ConversionServiceImpl(ConversionRepository conversionRepository, ConversionMapper conversionMapper) {
        this.conversionRepository = conversionRepository;
        this.conversionMapper = conversionMapper;
    }

    @Override
    public ConversionResponseDto getById(UUID id) {
        return conversionRepository.findById(id).map(conversionMapper::toConversionResponse).orElseThrow(() -> new EntityNotFoundException(String.format("Conversion with id %s not found", id)));
    }

    @Override
    public List<ConversionResponseDto> getAllConversions() {
        return conversionRepository.findAll().stream()
                .map(conversionMapper::toConversionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConversionResponseDto update(ConversionRequestDto conversionRequestDto) {
        return null;
    }

    @Override
    public ConversionResponseDto create(ConversionRequestDto conversionRequestDto) {
        Conversion conversion = conversionMapper.toConversion(conversionRequestDto);
        Conversion savedConversion = conversionRepository.save(conversion);
        return conversionMapper.toConversionResponse(savedConversion);
    }
}
