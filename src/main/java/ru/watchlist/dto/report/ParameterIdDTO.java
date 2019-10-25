package ru.watchlist.dto.report;

import lombok.Data;

@Data
public class ParameterIdDTO {

    private Long id;
    private String title;
    private long groupId;
    private boolean cumulative;

}
