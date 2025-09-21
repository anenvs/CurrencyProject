package ru.sidorov.currencyproject.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sidorov.currencyproject.dto.HelloResponseDto;
import ru.sidorov.currencyproject.feign.HelloApiClient;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class CurrencyUpdateJob {
    private final HelloApiClient helloApiClient;

    @Scheduled(fixedRate = 5000)
    @Async
    public void sayHello() {
        HelloResponseDto helloResponseDto = helloApiClient.hello("Ivan");
        log.info("say hello {}", helloResponseDto);
    }
}
