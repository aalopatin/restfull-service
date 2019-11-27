package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class ParameterValue {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Parameter parameter;
    private float value;

}
