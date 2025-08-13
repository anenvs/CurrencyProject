package ru.sidorov.currencyproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "conversion")
@Getter
@Setter
@NoArgsConstructor
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_currency", nullable = false, foreignKey = @ForeignKey(name = "fk_conversion_from_currency"))
    private Currency fromCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_currency", nullable = false, foreignKey = @ForeignKey(name = "fk_conversion_to_currency"))
    private Currency toCurrency;

    @Column(name = "amount", precision = 19, scale = 6, nullable = false)
    private BigDecimal amount;

    @Column(name = "converted_amount", precision = 19, scale = 6, nullable = false)
    private BigDecimal convertedAmount;

    @Column(name = "rate", precision = 19, scale = 6, nullable = false)
    private BigDecimal rate;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

}
