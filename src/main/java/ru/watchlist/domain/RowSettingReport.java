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

    @ManyToOne
    private Parameter parameter;

    private boolean header;

    private String text;

}
