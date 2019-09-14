package ru.watchlist.dto.user;

import lombok.Data;
import ru.watchlist.validation.UniqueField;
import ru.watchlist.validation.UniqueFields;

import javax.validation.constraints.NotBlank;

@Data
@UniqueField(fieldName = UniqueFields.EMAIL, message = "Указанный email занят!")
public class UserEmailDTO {

    private Long id;

    @NotBlank(message = "Email должен быть заполнен!")
    private String email;

}
