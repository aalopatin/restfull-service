package ru.watchlist.dto.report;

import lombok.Data;
import ru.watchlist.domain.report.Standard;

@Data
public class TypeReportDTO {

    private Long id;
    private String title;
    private Standard standard;

}
