package ru.watchlist.domain.report;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @ManyToOne
    private GroupParameters group;
    private boolean cumulative;

}
