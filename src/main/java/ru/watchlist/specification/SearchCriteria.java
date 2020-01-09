package ru.watchlist.specification;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class SearchCriteria {

    private String key;
    private List<String> chainKey;
    private String operation;
    private String value;

    public SearchCriteria(String key, String operation, String value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.chainKey = Arrays.asList(key.split("\\."));
    }
}
