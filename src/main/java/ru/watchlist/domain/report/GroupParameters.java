package ru.watchlist.domain.report;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GroupParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private boolean basic;
    @ManyToOne
    private TypeReport typeReport;

}
