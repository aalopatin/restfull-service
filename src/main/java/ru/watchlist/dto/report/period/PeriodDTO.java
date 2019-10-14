package ru.watchlist.dto.report.period;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.watchlist.domain.report.period.TypePeriod;

import java.time.LocalDate;
import java.util.Date;

@Data
public class PeriodDTO {

    private String id;

//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startPeriod;

//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endPeriod;

    private TypePeriod type;

}
