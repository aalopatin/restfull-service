package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Currency;
import java.util.List;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Data
@Entity
public class Report {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Period period;
    @ManyToOne
    private TypeReport typeReport;
    private int multiplicity;
    private String currencyCode;
    @OneToMany
    List<ParameterValue> parameters;
}
