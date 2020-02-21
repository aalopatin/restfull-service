package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;
    private String title;
    private String fullTitle;

    @Column(length = 1500)
    private String description;

    @Column(name = "LOGO")
    private String logo;

}
