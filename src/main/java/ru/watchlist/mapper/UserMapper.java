package ru.watchlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Profile;
import ru.watchlist.domain.user.Role;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.ProfileDTO;
import ru.watchlist.dto.UserDTO;
import ru.watchlist.security.jwt.JwtUser;

import java.util.List;

@Mapper(componentModel = "spring", imports = Role.class)
public interface UserMapper {

    //userDTO
    List<UserDTO> toUserDTOList(List<User> users);

    UserDTO toUserDTO(User user);

    User fromUserDTO(UserDTO userDTO);

    void fromUserDTOToUser(UserDTO userDTO, @MappingTarget User user);

    //ProfileDTO
    @Mapping(source = "roles", target = "scope")
    ProfileDTO toProfileDTO(User user);

    //JwtUser
    JwtUser toJwtUser(User user);

    //user
    void fromUserToUser(User source, @MappingTarget User target);
}
