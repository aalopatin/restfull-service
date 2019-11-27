package ru.watchlist.dto;

import lombok.Data;

@Data
public class ParameterIdDTO extends ParameterAbstract {

    private long groupId;
    private String groupTitle;

}
