package ru.watchlist.dto.report;

import lombok.Data;
import ru.watchlist.domain.enums.TypePeriod;

import java.time.LocalDate;

@Data
public class PeriodDTO {

    private String id;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
    private TypePeriod type;
    private Byte number;

}
