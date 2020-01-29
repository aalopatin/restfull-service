package ru.watchlist.dto.groupparameter;

import lombok.Data;

@Data
public abstract class GroupParameterAbstract {

    private Long id;
    private String title;
    private boolean common;

}
