package ru.watchlist.dto.report;

import lombok.Data;

@Data
public class ParameterDTO {

    private Long id;
    private String title;
    private GroupParametersDTO group;
    private boolean cumulative;

}

