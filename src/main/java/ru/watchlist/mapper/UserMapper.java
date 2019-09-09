package ru.watchlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.watchlist.domain.user.Role;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.UserAuthDTO;
import ru.watchlist.dto.UserDTO;
import ru.watchlist.dto.UserProfileDTO;
import ru.watchlist.security.jwt.JwtUser;

import java.util.List;

@Mapper(componentModel = "spring", imports = Role.class)
public interface UserMapper {

    //UserDTO
    List<UserDTO> toUserDTOList(List<User> users);
    UserDTO toUserDTO(User user);
    User fromUserDTO(UserDTO userDTO);
    void fromUserDTOToUser(UserDTO userDTO, @MappingTarget User user);

    //UserAuthDTO
    UserAuthDTO toUserAuthDTO(User user);

    //UserProfileDTO
    UserProfileDTO toUserProfileDTO(User user);

    //JwtUser
    JwtUser toJwtUser(User user);

    //user
    void fromUserToUser(User source, @MappingTarget User target);
}
