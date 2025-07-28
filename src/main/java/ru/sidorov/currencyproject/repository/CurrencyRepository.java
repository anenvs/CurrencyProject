package ru.sidorov.currencyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sidorov.currencyproject.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
