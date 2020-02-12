package ru.watchlist.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArrayDTO<T> {
    private List<T> data;
}
