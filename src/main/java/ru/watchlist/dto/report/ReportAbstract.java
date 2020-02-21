package ru.watchlist.dto.report;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.watchlist.dto.DTOAbstract;

import java.util.Currency;
import java.util.Date;

@Getter
@Setter
public abstract class ReportAbstract extends DTOAbstract {

    private Long id;
    private int multiplicity;
    private String currency;

}
