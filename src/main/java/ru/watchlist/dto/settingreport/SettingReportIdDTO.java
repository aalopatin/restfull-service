package ru.watchlist.dto.settingreport;

import lombok.Data;
import ru.watchlist.dto.RowSettingReportDTO;

import java.util.List;

@Data
public class SettingReportIdDTO extends SettingReportAbstract {

    private Long companyId;
    private String companyTitle;

    private Long groupId;
    private String groupTitle;

    private List<RowSettingReportDTO> rows;

}
