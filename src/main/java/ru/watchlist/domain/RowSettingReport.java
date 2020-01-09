package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class RowSettingReport {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Parameter parameter;

    @OneToMany
    private List<RowSettingReport> childParameters;

}
