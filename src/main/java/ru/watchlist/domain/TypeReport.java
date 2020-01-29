package ru.watchlist.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.TypeReportService;

import javax.persistence.*;
import javax.swing.*;

import java.util.Set;

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
