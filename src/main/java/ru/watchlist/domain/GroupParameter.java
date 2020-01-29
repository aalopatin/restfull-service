package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.*;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Entity
@Data
public class GroupParameter {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;
    private String title;
    private boolean common;
    @ManyToOne
    @JoinColumn(name = "TYPE_REPORT_ID", nullable = false)
    private TypeReport typeReport;

}
