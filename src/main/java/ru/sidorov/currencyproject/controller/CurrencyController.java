package ru.sidorov.currencyproject.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.dto.CurrencyResponseDto;
import ru.sidorov.currencyproject.kafka.producer.CurrencyProducer;
import ru.sidorov.currencyproject.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController implements ICurrencyController {
    private final CurrencyService currencyService;
    private final MeterRegistry meterRegistry;
    private final Timer timer;
    private final CurrencyProducer producer;

    public CurrencyController(CurrencyService currencyService, MeterRegistry meterRegistry, CurrencyProducer producer) {
        this.currencyService = currencyService;
        this.meterRegistry = meterRegistry;
        timer = Timer.builder("CurrencyTimer").tags("controller", "currency").description("currency timer metrics").register(meterRegistry);
        this.producer = producer;
    }
    @Override
    public ResponseEntity<CurrencyResponseDto> getByCode(String code) {
        Timer.Sample sample = Timer.start(meterRegistry);
        CurrencyResponseDto currencyRS = currencyService.getByCode(code);
        sample.stop(timer);
        return ResponseEntity.ok(currencyRS);
    }

    @Override
    public ResponseEntity<List<CurrencyResponseDto>> getAll() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @Override
    public ResponseEntity<Void> create(CurrencyRequestDto currencyRequestDto) {
        producer.sendCurrencyEvent(currencyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}