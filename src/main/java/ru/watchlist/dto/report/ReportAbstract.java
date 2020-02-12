package ru.watchlist.dto.report;

import lombok.Data;

import java.util.Currency;

@Data
public class ReportAbstract {

    private Long id;
    private int multiplicity;
    private String currency;

}
