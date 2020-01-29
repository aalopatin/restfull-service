package ru.watchlist.dto.settingreport;

import lombok.Data;

import java.util.List;

@Data
public class SettingReportIdDTO extends SettingReportAbstract {

    private Long companyId;
    private Long typeReportId;
    private Long groupId;

    private List<RowSettingReportIdDTO> rows;

}
