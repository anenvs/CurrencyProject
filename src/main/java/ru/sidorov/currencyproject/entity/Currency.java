package ru.sidorov.currencyproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @Id
    @Column(name = "code", length = 3, nullable = false)
    private String code;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

}
