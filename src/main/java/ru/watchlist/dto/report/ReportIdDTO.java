package ru.watchlist.dto.report;

import lombok.Data;
import ru.watchlist.domain.RowReport;

import java.util.Set;

@Data
public class ReportIdDTO extends ReportAbstract {

    private Long companyId;
    private String periodId;
    private Long typeReportId;
    Set<RowReportIdDTO> rows;

}
