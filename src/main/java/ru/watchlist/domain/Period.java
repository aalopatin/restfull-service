package ru.watchlist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import static ru.watchlist.config.Constants.ID_PERIOD_GENERATOR;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Period {

    @Id
    @GeneratedValue(generator = ID_PERIOD_GENERATOR)
    private String id;
    private LocalDate startPeriod;
    private LocalDate endPeriod;

    @Enumerated(EnumType.STRING)
    private TypePeriod type;

}
