package ru.watchlist.domain.report.parameter;

import lombok.Data;
import ru.watchlist.domain.report.Report;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
//@Entity
public class ParameterValue {

//    @Id
    private Long id;
    private Report report;
    private Parameter parameter;
    private double value;


}
