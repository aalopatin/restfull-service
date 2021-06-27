package ru.watchlist.dto.report;

import lombok.Data;

@Data
public class RowReportIdDTO {

    private Long parameterId;
    private String parameterTitle;
    private float value;

}
