package ru.watchlist.domain.company;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String fullTitle;

}
