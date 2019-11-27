package ru.watchlist.dto;

import lombok.Data;
import ru.watchlist.validation.UniqueField;
import ru.watchlist.validation.UniqueFields;

import javax.validation.constraints.NotBlank;

@Data
public class UserPasswordDTO {

    @NotBlank(message = "Пароль должен быть заполнен!")
    private String password;

}
