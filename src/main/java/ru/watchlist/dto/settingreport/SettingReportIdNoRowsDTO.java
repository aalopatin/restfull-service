package ru.watchlist.dto.settingreport;

import lombok.Data;

@Data
public class SettingReportIdNoRowsDTO extends SettingReportAbstract {
    private Long companyId;
    private Long typeReportId;
    private Long groupId;
}
