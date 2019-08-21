package ru.watchlist.dto;

import lombok.Data;
import ru.watchlist.dto.validation.UniqueField;
import ru.watchlist.dto.validation.UniqueFields;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@UniqueField(fieldName = UniqueFields.EMAIL, message = "Указанный email занят!")
@UniqueField(fieldName = UniqueFields.USERNAME, message = "Указанное имя пользователя занято!")
public class UserDTO {

    private Long id;

    @NotBlank(message = "Имя пользователя должно быть заполенно!")
    private String username;

    @NotBlank(message = "Пароль должен быть заполенн!")
    private String password;

    @NotBlank(message = "Email должен быть заполнен!")
    @Email(message = "Не правильно введн email!")
    private String email;

    private boolean active;
    private String activationCode;

}
