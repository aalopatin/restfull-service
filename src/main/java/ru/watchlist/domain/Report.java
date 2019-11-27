package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Currency;
import java.util.List;

@Data
@Entity
public class Report {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Period period;
    @ManyToOne
    private TypeReport typeReport;
    private int multiplicity;
    private Currency currency;
    @OneToMany
    List<ParameterValue> parameters;
}
