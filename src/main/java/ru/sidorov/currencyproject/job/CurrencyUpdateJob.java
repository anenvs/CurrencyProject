package ru.sidorov.currencyproject.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class CurrencyUpdateJob {
    @Scheduled(fixedRate = 5000)
    @Async
    public void currencyUpdate() {
        log.info("Currencyproject update {}", LocalDateTime.now());
    }
}
