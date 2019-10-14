package ru.watchlist.domain.report.parameter;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
//@Entity
public class Parameter {

//    @Id
    private Long id;
    private boolean cumulative;

}
