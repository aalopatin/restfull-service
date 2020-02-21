package ru.watchlist.dto.report;

import lombok.Data;

import java.util.Set;

@Data
public class ReportIdDTO extends ReportAbstract {

    private Long companyId;
    private String companyTitle;

    private String periodId;

    private Long typeReportId;
    private String typeReportTitle;

    Set<RowReportIdDTO> rows;

}
