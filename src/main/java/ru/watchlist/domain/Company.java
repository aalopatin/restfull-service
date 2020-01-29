package ru.watchlist.domain;

import lombok.Data;
import static ru.watchlist.config.Constants.ID_GENERATOR;

import javax.persistence.*;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;
    private String title;
    private String fullTitle;

}
