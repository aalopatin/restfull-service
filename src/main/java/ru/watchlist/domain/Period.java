package ru.watchlist.domain;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;
import ru.watchlist.domain.enums.TypePeriod;
import ru.watchlist.domain.valuegenerator.NumberPeriodGenerator;

import javax.persistence.*;
import java.time.LocalDate;

import static ru.watchlist.config.Constants.ID_PERIOD_GENERATOR;

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

    @GeneratorType(type = NumberPeriodGenerator.class)
    private Byte number;

}
