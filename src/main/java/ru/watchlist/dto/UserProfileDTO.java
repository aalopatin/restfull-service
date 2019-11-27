package ru.watchlist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.watchlist.validation.UniqueField;
import ru.watchlist.validation.UniqueFields;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserProfileDTO {

    private Long id;
    private String username;
    private String email;

}
