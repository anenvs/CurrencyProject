package ru.sidorov.currencyproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sidorov.currencyproject.dto.ConversionResponseDto;
import ru.sidorov.currencyproject.dto.ExchangeRateRequestDto;
import ru.sidorov.currencyproject.dto.ExchangeRateResponseDto;
import ru.sidorov.currencyproject.entity.Currency;
import ru.sidorov.currencyproject.entity.ExchangeRate;
import ru.sidorov.currencyproject.mapper.ExchangeRateMapperV2;
import ru.sidorov.currencyproject.repository.ExchangeRateRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceImplTest {
    @Mock
    private ExchangeRateRepository exchangeRateRepository;
    @Mock
    private ExchangeRateMapperV2 exchangeRateMapper;
    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    private ExchangeRate testExchangeRate;
    private ExchangeRateRequestDto testExchangeRateRequestDto;
    private ExchangeRateResponseDto testExchangeRateResponseDto;
    private UUID testUuid;

    @BeforeEach
    public void setUp() {
        testUuid = UUID.randomUUID();
        testExchangeRate = ExchangeRate.builder()
                .id(testUuid)
                .rate(BigDecimal.valueOf(100.00))
                .currency(new Currency("USD", "US Dollar"))
                .build();

        testExchangeRateRequestDto = new ExchangeRateRequestDto(
                "USD",
                BigDecimal.valueOf(100.00)
        );

        testExchangeRateResponseDto = new ExchangeRateResponseDto(
                "US Dollar",
                BigDecimal.valueOf(100.00),
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("Успешное получение ExchangeRate")
    void getExchangeRate() {
        when(exchangeRateRepository.findById(testUuid)).thenReturn(Optional.of(testExchangeRate));
        when(exchangeRateMapper.toExchangeRateResponseDto(testExchangeRate)).thenReturn(testExchangeRateResponseDto);
        ExchangeRateResponseDto result = exchangeRateService.getById(testUuid);
        assertNotNull(result);
        assertEquals(testExchangeRateResponseDto, result);
    }
}
