package ru.watchlist.domain;

import lombok.Data;
import ru.watchlist.domain.enums.TypePeriod;

import static ru.watchlist.config.Constants.ID_PERIOD_GENERATOR;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Period {

    @Id
    @GeneratedValue(generator = ID_PERIOD_GENERATOR)
    @Column(length = 8)
    private String id;

    @Column(nullable = false)
    private LocalDate startPeriod;

    @Column(nullable = false)
    private LocalDate endPeriod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypePeriod type;

    private Byte number;

}
