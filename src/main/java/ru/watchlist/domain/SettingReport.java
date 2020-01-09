package ru.watchlist.domain;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class SettingReport {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Company company;
    private boolean common;

    @OneToOne
    private TypeReport typeReport;

    @OneToOne
    private GroupParameters groupParameters;

    @OneToMany
    private List<RowSettingReport> rowsSettingReport;

}
