package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Data
@Entity
public class SettingReport {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;

    private String title;

    private boolean common;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_REPORT_ID", nullable = false)
    private TypeReport typeReport;

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupParameter group;

    @ElementCollection
    @CollectionTable(name = "ROW_SETTING_REPORT")
    @OrderColumn
    private List<RowSettingReport> rows = new ArrayList<>();

}
