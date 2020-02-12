package ru.watchlist.domain;

import lombok.Data;
import ru.watchlist.domain.enums.Standard;

import javax.persistence.*;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Data
@Entity
public class TypeReport {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Standard standard;

}
