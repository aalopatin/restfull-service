package ru.watchlist.dto;

import lombok.Data;
import ru.watchlist.domain.user.Role;

import java.util.Set;

@Data
public class ProfileDTO {

    private String username;
    private String Email;
    private Set<Role> scope;

}
