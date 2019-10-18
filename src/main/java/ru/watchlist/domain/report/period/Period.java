package ru.watchlist.domain.report.period;

import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Period {

    @Id
    @GenericGenerator(name = "period_id", strategy = "ru.watchlist.idgenerator.PeriodIdentifierGenerator")
    @GeneratedValue(generator = "period_id")
    private String id;
    private LocalDate startPeriod;
    private LocalDate endPeriod;

    @Enumerated(EnumType.STRING)
    private TypePeriod type;

}
