package ru.watchlist.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TypeReport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private Standard standard;

}
