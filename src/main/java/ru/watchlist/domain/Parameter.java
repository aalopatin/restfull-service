package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Data
@Entity
public class Parameter {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;
    private String title;
    @ManyToOne
    private GroupParameter group;
    private boolean cumulative;

}
