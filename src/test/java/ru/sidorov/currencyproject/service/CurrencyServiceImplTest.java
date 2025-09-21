package ru.sidorov.currencyproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sidorov.currencyproject.dto.CurrencyRequestDto;
import ru.sidorov.currencyproject.dto.CurrencyResponseDto;
import ru.sidorov.currencyproject.entity.Currency;
import ru.sidorov.currencyproject.exception.EntityNotFoundException;
import ru.sidorov.currencyproject.mapper.CurrencyMapperV2;
import ru.sidorov.currencyproject.repository.CurrencyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private CurrencyMapperV2 currencyMapper;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    private Currency testCurrency;
    private CurrencyResponseDto testCurrencyResponseDTO;
    private CurrencyRequestDto testCurrencyRequestDTO;
    private final String testCode = "USD";

    @BeforeEach
    void setUp() {
        testCurrency = new Currency("USD", "US Dollar");
        testCurrencyResponseDTO = new CurrencyResponseDto(
                "USD",
                "US Dollar",
                "USD - US Dollar",
                LocalDateTime.now()
        );
        testCurrencyRequestDTO = new CurrencyRequestDto("USD", "US Dollar");
    }

    @Test
    @DisplayName("Успешное получение валюты по коду")
    void getCurrencyByCodeSuccess() {
        // when
        when(currencyRepository.findById(testCode)).thenReturn(Optional.of(testCurrency));
        when(currencyMapper.toCurrencyDto(testCurrency)).thenReturn(testCurrencyResponseDTO);

        // do
        CurrencyResponseDto result = currencyService.getByCode(testCode);

        // then
        assertNotNull(result);
        assertEquals(testCurrencyResponseDTO, result);
        verify(currencyRepository, times(1)).findById(testCode);
        verify(currencyMapper, times(1)).toCurrencyDto(testCurrency);
        verifyNoMoreInteractions(currencyRepository, currencyMapper);
    }

    @Test
    @DisplayName("Неуспешное получение валюты по коду - валюта не найдена")
    void getCurrencyByCodeUnsuccess() {
        // when
        when(currencyRepository.findById(testCode)).thenReturn(Optional.empty());

        // do + then
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> currencyService.getByCode(testCode)
        );

        assertEquals(
                String.format("Currency with code %s not found", testCode),
                exception.getMessage()
        );
        verify(currencyRepository, times(1)).findById(testCode);
        verifyNoInteractions(currencyMapper);
        verifyNoMoreInteractions(currencyRepository);
    }

    @Test
    @DisplayName("Успешное получение всех валют")
    void getAllCurrenciesSuccess() {
        // given
        Currency secondCurrency = new Currency("EUR", "Euro");
        CurrencyResponseDto secondCurrencyResponseDTO = new CurrencyResponseDto(
                "EUR",
                "Euro",
                "EUR - Euro",
                LocalDateTime.now()
        );

        List<Currency> currencies = List.of(testCurrency, secondCurrency);
        List<CurrencyResponseDto> expectedResponse = List.of(testCurrencyResponseDTO, secondCurrencyResponseDTO);

        // when
        when(currencyRepository.findAll()).thenReturn(currencies);
        when(currencyMapper.toCurrencyDto(testCurrency)).thenReturn(testCurrencyResponseDTO);
        when(currencyMapper.toCurrencyDto(secondCurrency)).thenReturn(secondCurrencyResponseDTO);

        // do
        List<CurrencyResponseDto> result = currencyService.getAllCurrencies();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedResponse, result);
        verify(currencyRepository, times(1)).findAll();
        verify(currencyMapper, times(1)).toCurrencyDto(testCurrency);
        verify(currencyMapper, times(1)).toCurrencyDto(secondCurrency);
        verifyNoMoreInteractions(currencyRepository, currencyMapper);
    }

    @Test
    @DisplayName("Успешное создание валюты")
    void createCurrencySuccess() {
        // given
        Currency savedCurrency = new Currency("USD", "US Dollar");

        // when
        when(currencyMapper.toCurrency(testCurrencyRequestDTO)).thenReturn(testCurrency);
        when(currencyRepository.save(testCurrency)).thenReturn(savedCurrency);
        when(currencyMapper.toCurrencyDto(savedCurrency)).thenReturn(testCurrencyResponseDTO);

        // do
        CurrencyResponseDto result = currencyService.createCurrency(testCurrencyRequestDTO);

        // then
        assertNotNull(result);
        assertEquals(testCurrencyResponseDTO, result);
        verify(currencyMapper, times(1)).toCurrency(testCurrencyRequestDTO);
        verify(currencyRepository, times(1)).save(testCurrency);
        verify(currencyMapper, times(1)).toCurrencyDto(savedCurrency);
        verifyNoMoreInteractions(currencyRepository, currencyMapper);
    }

    @Test
    @DisplayName("Получение всех валют - пустой список")
    void getAllCurrenciesEmpty() {
        // when
        when(currencyRepository.findAll()).thenReturn(List.of());

        // do
        List<CurrencyResponseDto> result = currencyService.getAllCurrencies();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(currencyRepository, times(1)).findAll();
        verifyNoInteractions(currencyMapper);
        verifyNoMoreInteractions(currencyRepository);
    }
}