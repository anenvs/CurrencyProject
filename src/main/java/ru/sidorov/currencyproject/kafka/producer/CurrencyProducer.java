package ru.sidorov.currencyproject.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyProducer {
    private final String BINDING_NAME = "currency-producer-out-0";

    private final StreamBridge streamBridge;

    public void sendCurrencyEvent(CurrencyRequestDto currencyRq) {
        Message<CurrencyRequestDto> message = MessageBuilder.withPayload(currencyRq)
                .setHeader(MessageHeaders.CONTENT_TYPE, "application/json")
                .build();
    log.info("Sending currency event: {}", message);
    streamBridge.send(BINDING_NAME, message);
    }
}
