package ru.watchlist.dto.report;

import lombok.Data;

@Data
public abstract class ParameterAbstract {

    private Long id;
    private String title;
    private boolean cumulative;

}
