package ru.watchlist.dto;

import lombok.Data;

@Data
public abstract class CompanyAbstract {

    private Long id;
    private String title;
    private String fullTitle;

}
