package ru.watchlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.watchlist.domain.user.Role;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring", imports = Role.class)
public interface UserMapper {

    List<UserDTO> toUserDTOList(List<User> users);

    UserDTO toUserDTO(User user);

    User fromUserDTO(UserDTO userDTO);

    void fromUserDTOToUser(UserDTO userDTO, @MappingTarget User user);
}
