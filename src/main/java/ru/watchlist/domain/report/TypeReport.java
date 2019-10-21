package ru.watchlist.domain.report;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TypeReport {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private Standard standard;

}
