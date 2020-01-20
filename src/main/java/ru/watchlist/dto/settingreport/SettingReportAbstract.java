package ru.watchlist.dto.settingreport;

import lombok.Data;

@Data
public abstract class SettingReportAbstract {

    private Long id;
    private String title;
    private boolean common;

}
