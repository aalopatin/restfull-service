package ru.watchlist.dto;

import lombok.Data;

@Data
public abstract class GroupParametersAbstract {

    private Long id;
    private String title;
    private boolean basic;

}
