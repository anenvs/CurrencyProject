package ru.sidorov.currencyproject.service;

import ru.sidorov.currencyproject.entity.Conversion;

import java.util.List;
import java.util.UUID;

public interface ConversionService {
    Conversion getById(UUID id);
    List<Conversion> getAllConversions();
    Conversion update(Conversion conversion);
}
