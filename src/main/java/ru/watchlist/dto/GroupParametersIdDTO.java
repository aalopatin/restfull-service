package ru.watchlist.dto;

import lombok.Data;

@Data
public class GroupParametersIdDTO {

    private Long id;
    private String title;
    private boolean basic;
    private Long typeReportId;

}
