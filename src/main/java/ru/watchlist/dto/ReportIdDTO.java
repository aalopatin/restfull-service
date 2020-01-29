package ru.watchlist.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportIdDTO {

    private Long id;
    private Long companyId;
    private String companyTitle;
    private String periodId;
    private Long typeReportId;
    private String typeReportTitle;
    private int multiplicity;
    private String currencyCode;
    List<ParameterValueIdDTO> parameters;

}
