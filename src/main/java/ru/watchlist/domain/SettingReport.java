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

    private String title;

    private boolean common;

    @ManyToOne
    private Company company;

    @OneToOne
    private GroupParameters group;

    @OneToMany
    private List<RowSettingReport> rows;

}
