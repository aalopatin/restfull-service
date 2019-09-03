package ru.watchlist.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UniqueFields {

    EMAIL("email"),
    USERNAME("username");

    private String name;

}
