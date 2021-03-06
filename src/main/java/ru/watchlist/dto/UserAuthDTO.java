package ru.watchlist.dto;

import lombok.Data;
import ru.watchlist.domain.enums.Role;

import java.util.Set;

@Data
public class UserAuthDTO {

    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;

}
