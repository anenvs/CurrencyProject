package ru.sidorov.currencyproject.config.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CounterMetrics {

    private final MeterRegistry meterRegistry;
    private Counter currencyCreationCounter;
    private Counter conversionCounter;
    private Counter exchangeRateUpdateCounter;

    @PostConstruct
    public void init() {
        currencyCreationCounter = Counter.builder("currency.creation.count")
                .description("Количество созданных валют")
                .register(meterRegistry);

        conversionCounter = Counter.builder("conversion.operations.count")
                .description("Количество операций конвертации")
                .register(meterRegistry);

        exchangeRateUpdateCounter = Counter.builder("exchange.rate.update.count")
                .description("Количество обновлений курсов валют")
                .register(meterRegistry);
    }

    public void incrementCurrencyCreation() {
        currencyCreationCounter.increment();
    }

    public void incrementConversion() {
        conversionCounter.increment();
    }

    public void incrementExchangeRateUpdate() {
        exchangeRateUpdateCounter.increment();
    }
}