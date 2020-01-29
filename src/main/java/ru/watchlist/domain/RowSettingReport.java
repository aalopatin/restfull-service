package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Data
@Embeddable
public class RowSettingReport {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeRowReport typeRow;

    @ManyToOne(fetch = FetchType.LAZY)
    private Parameter parameter;

    private String title;

}
