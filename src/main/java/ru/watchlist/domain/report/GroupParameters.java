package ru.watchlist.domain.report;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class GroupParameters {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private TypeReport typeReport;

}
