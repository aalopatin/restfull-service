package ru.watchlist.dto.report;

import lombok.Data;
import ru.watchlist.domain.report.TypeReport;

@Data
public class GroupParametersDTO {

    private Long id;
    private String title;
    private TypeReport typeReport;

}
