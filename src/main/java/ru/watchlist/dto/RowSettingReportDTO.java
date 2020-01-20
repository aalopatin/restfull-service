package ru.watchlist.dto;

import lombok.Data;

@Data
public class RowSettingReportDTO {

    private Long id;
    private Long parameterId;
    private boolean header;
    private String title;

}
