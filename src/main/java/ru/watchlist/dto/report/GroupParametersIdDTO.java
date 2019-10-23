package ru.watchlist.dto.report;

import lombok.Data;

@Data
public class GroupParametersIdDTO {

    private Long id;
    private String title;
    private boolean basic;
    private Long typeReport;

}
