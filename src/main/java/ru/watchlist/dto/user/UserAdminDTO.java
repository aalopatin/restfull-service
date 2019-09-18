package ru.watchlist.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.watchlist.domain.user.Role;
import ru.watchlist.dto.user.views.UserAdminViews;

import java.util.Set;

@Data
public class UserAdminDTO {

    private Long id;
    private String username;
    private String email;

    @JsonView(UserAdminViews.SaveUser.class)
    private boolean active;

    @JsonView(UserAdminViews.SaveUser.class)
    private String activationCode;

    @JsonView(UserAdminViews.SaveUser.class)
    private Set<Role> roles;

}
