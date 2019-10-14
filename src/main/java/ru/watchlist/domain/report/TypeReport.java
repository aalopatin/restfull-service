package ru.watchlist.domain.report;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
//@Entity
public class TypeReport {

//    @Id
    private Long id;
    private String title;

}
