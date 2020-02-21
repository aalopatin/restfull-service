package ru.watchlist.dto.report;

import lombok.Data;

@Data
public class ReportIdNoRowsDTO extends ReportAbstract {

    private Long companyId;
    private String companyTitle;

    private String periodId;

    private Long typeReportId;
    private String typeReportTitle;

}
