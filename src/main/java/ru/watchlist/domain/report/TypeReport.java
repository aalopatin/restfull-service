package ru.watchlist.domain.report;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
public class TypeReport {

    @Id
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private Standard standard;

}
