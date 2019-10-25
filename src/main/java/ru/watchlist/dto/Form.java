package ru.watchlist.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Form {

    Object data;
    Map<String, Object> payloads;

}
