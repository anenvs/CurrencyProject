package ru.sidorov.currencyproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.currencyproject.dto.ConversionRequestDto;
import ru.sidorov.currencyproject.dto.ConversionResponseDto;
import ru.sidorov.currencyproject.kafka.producer.ConversionProducer;
import ru.sidorov.currencyproject.service.ConversionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/conversion")
public class ConversionController implements IConversionController {
    private final ConversionProducer producer;
    private final ConversionService conversionService;

    public ConversionController(ConversionProducer producer, ConversionService conversionService) {
        this.producer = producer;
        this.conversionService = conversionService;
    }

    @Override
    public ResponseEntity<List<ConversionResponseDto>> getAll() {
        return ResponseEntity.ok(conversionService.getAllConversions());
    }

    @Override
    public ResponseEntity<ConversionResponseDto> create(ConversionRequestDto conversionRequestDto) {
        producer.sendConversionEvent(conversionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
