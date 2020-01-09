package ru.watchlist.dto;

import lombok.Data;

@Data
public class ParameterValueIdDTO {

    private Long id;
    private Long parameterId;
    private String ParameterTitle;
    private float value;

}
