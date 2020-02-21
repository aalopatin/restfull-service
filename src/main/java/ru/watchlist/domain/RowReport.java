package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Embeddable
public class RowReport {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARAMETER_ID", nullable = false)
    private Parameter parameter;
    private Float value;

}
