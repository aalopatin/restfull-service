package ru.watchlist.dto.parameter;

import lombok.Data;

@Data
public abstract class ParameterAbstract {

    private Long id;
    private String title;
    private boolean cumulative;

}
