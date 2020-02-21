package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Data
@Entity
@Table(uniqueConstraints =
        @UniqueConstraint(
                name = "UNQ_REPORT",
                columnNames = { "COMPANY_ID", "PERIOD_ID", "TYPE_REPORT_ID", "CURRENCY" }
        ))
public class Report extends EntityDate {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERIOD_ID", nullable = false)
    private Period period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_REPORT_ID", nullable = false)
    private TypeReport typeReport;

    @JoinColumn(name = "MULTIPLICITY", nullable = false)
    private int multiplicity;

    @JoinColumn(name = "CURRENCY", nullable = false)
    private String currency;

    @ElementCollection
    @CollectionTable(name = "ROW_REPORT")
    Set<RowReport> rows = new HashSet<>();
}
