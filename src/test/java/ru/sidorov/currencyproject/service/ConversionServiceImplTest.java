package ru.sidorov.currencyproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sidorov.currencyproject.dto.ConversionResponseDto;
import ru.sidorov.currencyproject.entity.Conversion;
import ru.sidorov.currencyproject.entity.Currency;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.mapper.ConversionMapperV2;
import ru.sidorov.currencyproject.repository.ConversionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConversionServiceImplTest {
    @Mock
    private ConversionRepository conversionRepository;
    @Mock
    private ConversionMapperV2 conversionMapper;

    @InjectMocks
    private ConversionServiceImpl conversionService;

    private UUID testUuid;
    private Conversion testConversion;
    private ConversionResponseDto testConversionResponseDTO;

    @BeforeEach
    void setUp() {
        testUuid = UUID.randomUUID();
        testConversion = Conversion.builder()
                .amount(BigDecimal.valueOf(100.00))
                .convertedAmount(BigDecimal.valueOf(112.00))
                .date(LocalDateTime.now())
                .fromCurrency(new Currency("USD", "Dollar"))
                .toCurrency(new Currency("RUR", "Russian ruble"))
                .id(testUuid)
                .rate(BigDecimal.valueOf(10.00))
                .build();
        testConversionResponseDTO = ConversionResponseDto.builder()
                .amount(BigDecimal.valueOf(100.00))
                .convertedAmount(BigDecimal.valueOf(112.00))
                .fromCurrencyCode("USD")
                .toCurrencyCode("RUR")
                .id(testUuid)
                .rate(BigDecimal.valueOf(10.00))
                .build();
    }

    @Test
    @DisplayName("Успешное получение конверсии")
    void getConversionByIdSuccess()  {
        // when
        when(conversionRepository.findById(testUuid)).thenReturn(Optional.of(testConversion));
        when(conversionMapper.toConversionResponse(testConversion)).thenReturn(testConversionResponseDTO);
        // do
        ConversionResponseDto result = conversionService.getById(testUuid);
        //then
        assertNotNull(result);
        assertEquals(testConversionResponseDTO, result);
        verify(conversionRepository, times(1)).findById(testUuid);
        verify(conversionMapper, times(1)).toConversionResponse(testConversion); // Проверка количества вызовов
        verifyNoMoreInteractions(conversionRepository, conversionMapper); // После вызывов нет никаких других вызовов методов
    }

    @Test
    void getConversionByIdUnsuccess()  {
        // when
        when(conversionRepository.findById(testUuid)).thenReturn(Optional.empty());
        // do + then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,() -> conversionService.getById(testUuid));
        assertEquals(exception.getMessage(), String.format("Conversion with id %s not found", testUuid));
        verifyNoMoreInteractions(conversionRepository, conversionMapper);
    }


}