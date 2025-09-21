package ru.sidorov.currencyproject.config.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.sidorov.currencyproject.repository.CurrencyRepository;

@Component
@RequiredArgsConstructor
public class CurrencyMetric {
    private final CurrencyRepository currencyRepository;
    private final MeterRegistry meterRegistry;

    @PostConstruct
    public void init() {
        Gauge.builder("currency.size.current", currencyRepository, CrudRepository::count)
                .description("Метрика доступных валют")
                .register(meterRegistry);
    }
}
