package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.*;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Data
@Entity
public class ParameterValue {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;
    @ManyToOne
    private Parameter parameter;
    private float value;

}
