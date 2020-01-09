package ru.watchlist.dto;

import lombok.Data;

@Data
public class GroupParametersIdDTO extends GroupParametersAbstract {

    private Long typeReportId;
    private String typeReportTitle;

}
