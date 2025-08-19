package ru.sidorov.currencyproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "exchange_rate")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_code", nullable = false, foreignKey = @ForeignKey(name = "fk_exchange_rate_currency"))
    private Currency currency;

    @Column(name = "rate", precision = 19, scale = 6, nullable = false)
    private BigDecimal rate;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;
}
