package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.*;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Data
@Embeddable
public class RowReport {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARAMETER_ID", nullable = false)
    private Parameter parameter;
    private Float value;

}
