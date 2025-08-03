package ru.sidorov.currencyproject.service;

import org.springframework.stereotype.Service;
import ru.sidorov.currencyproject.dto.ConversionRequestDto;
import ru.sidorov.currencyproject.entity.Conversion;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.mapper.ConversionMapper;
import ru.sidorov.currencyproject.repository.ConversionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ConversionServiceImpl implements ConversionService {
    private final ConversionRepository conversionRepository;
    private final ConversionMapper conversionMapper;

    public ConversionServiceImpl(ConversionRepository conversionRepository, ConversionMapper conversionMapper) {
        this.conversionRepository = conversionRepository;
        this.conversionMapper = conversionMapper;
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
    public Conversion create(ConversionRequestDto conversionRequestDto) {
        Conversion conversion = conversionMapper.toConversion(conversionRequestDto);
        return conversionRepository.save(conversion); //TODO Conversion -> ConversionResponseDto
    }
}
