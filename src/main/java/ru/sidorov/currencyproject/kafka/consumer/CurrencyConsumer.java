package ru.sidorov.currencyproject.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.service.CurrencyService;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CurrencyConsumer {
    private final CurrencyService currencyService;

    @Bean
    public Consumer<Message<CurrencyRequestDto>> currencyRequestDto() {
        return message -> {
            Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
            log.info("Message received: {}", message.getPayload());
            currencyService.createCurrency(message.getPayload());
            if(acknowledgment != null) {
                acknowledgment.acknowledge();
            }
        };
    }
}
