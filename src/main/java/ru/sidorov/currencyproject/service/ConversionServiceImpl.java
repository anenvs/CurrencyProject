package ru.sidorov.currencyproject.service;

import org.springframework.stereotype.Service;
import ru.sidorov.currencyproject.dto.ConversionRequestDto;
import ru.sidorov.currencyproject.dto.ConversionResponseDto;
import ru.sidorov.currencyproject.entity.Conversion;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.mapper.ConversionMapper;
import ru.sidorov.currencyproject.mapper.ConversionMapperV2;
import ru.sidorov.currencyproject.repository.ConversionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ConversionServiceImpl implements ConversionService {
    private final ConversionRepository conversionRepository;
    private final ConversionMapperV2 conversionMapperV2;

    public ConversionServiceImpl(ConversionRepository conversionRepository, ConversionMapperV2 conversionMapperV2) {
        this.conversionRepository = conversionRepository;
        this.conversionMapperV2 = conversionMapperV2;
    }

    @Override
    public Conversion getById(UUID id) {
        return conversionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Conversion with id %s not found", id)));
    }

    @Override
    public List<Conversion> getAllConversions() {
        return conversionRepository.findAll();
    }

    @Override
    public Conversion update(Conversion conversion) {
        return null;
    }

    @Override
    public ConversionResponseDto create(ConversionRequestDto conversionRequestDto) {
        Conversion conversion = conversionMapperV2.toConversion(conversionRequestDto);
        Conversion savedConversion = conversionRepository.save(conversion);
        return conversionMapperV2.toConversionResponse(savedConversion);
    }
}
