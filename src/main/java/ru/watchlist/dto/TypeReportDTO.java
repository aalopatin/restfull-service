package ru.watchlist.dto;

import lombok.Data;
import ru.watchlist.domain.Standard;

@Data
public class TypeReportDTO {

    private Long id;
    private String title;
    private Standard standard;

}
