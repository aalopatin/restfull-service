package ru.watchlist.dto.user;

import lombok.Data;
import ru.watchlist.validation.UniqueField;
import ru.watchlist.validation.UniqueFields;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@UniqueField(fieldName = UniqueFields.EMAIL, message = "Указанный email занят!")
@UniqueField(fieldName = UniqueFields.USERNAME, message = "Указанное имя пользователя занято!")
public class UserRegistrationDTO {

    private Long id;

    @NotBlank(message = "Имя пользователя должно быть заполнено!")
    private String username;

    @NotBlank(message = "Пароль должен быть заполнен!")
    private String password;

    @NotBlank(message = "Email должен быть заполнен!")
    @Email(message = "Не правильно введен email!")
    private String email;

}
