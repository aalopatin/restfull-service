package ru.watchlist.dto;

import lombok.Data;
import ru.watchlist.domain.TypePeriod;

import java.time.LocalDate;

@Data
public class PeriodDTO {

    private String id;

//    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate startPeriod;

//    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate endPeriod;

    private TypePeriod type;

}
