package ru.watchlist.domain.report;

import lombok.Data;
import ru.watchlist.domain.company.Company;
import ru.watchlist.domain.report.parameter.ParameterValue;
import ru.watchlist.domain.report.period.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Currency;

@Data
//@Entity
public class Report {

//    @Id
//    @GeneratedValue
    private Long id;
    private Company company;
    private Period period;
    private TypeReport type;
    private Standard standard;
    private int multiplicity;
    private Currency currency;
    private ArrayList<ParameterValue> parametersValues;

}
