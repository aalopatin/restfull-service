package ru.watchlist.domain;

import lombok.Data;
import ru.watchlist.domain.enums.TypeRowReport;

import javax.persistence.*;

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
