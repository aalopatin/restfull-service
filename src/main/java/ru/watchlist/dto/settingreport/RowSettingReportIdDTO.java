package ru.watchlist.dto.settingreport;

import lombok.Data;
import ru.watchlist.domain.enums.TypeRowReport;

@Data
public class RowSettingReportIdDTO {

    private TypeRowReport typeRow;
    private Long parameterId;
    private String title;


}
