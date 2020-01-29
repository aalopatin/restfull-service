package ru.watchlist.dto.typereport;

import lombok.Data;
import ru.watchlist.domain.Standard;

@Data
public class TypeReportAbstract {

    private Long id;
    private String title;
    private Standard standard;

}
