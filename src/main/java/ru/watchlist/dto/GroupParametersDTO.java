package ru.watchlist.dto;

import lombok.Data;

@Data
public class GroupParametersDTO {

    private Long id;
    private String title;
    private boolean basic;
    private TypeReportDTO typeReport;

}
