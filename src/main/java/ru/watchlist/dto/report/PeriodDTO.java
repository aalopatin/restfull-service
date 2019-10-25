package ru.watchlist.dto.report;

import lombok.Data;
import ru.watchlist.domain.report.TypePeriod;

import java.time.LocalDate;

@Data
public class PeriodDTO {

    private String id;

//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startPeriod;

//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endPeriod;

    private TypePeriod type;

}
