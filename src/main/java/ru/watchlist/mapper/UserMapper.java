package ru.watchlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.http.ResponseEntity;
import ru.watchlist.domain.user.Role;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.UserAuthDTO;
import ru.watchlist.dto.user.UserAdminDTO;
import ru.watchlist.dto.user.UserProfileDTO;
import ru.watchlist.dto.user.UserEmailDTO;
import ru.watchlist.dto.user.UserRegistrationDTO;
import ru.watchlist.security.jwt.JwtUser;

import java.util.List;

@Mapper(componentModel = "spring", imports = Role.class)
public interface UserMapper {

    //-------User-------

    //---UserEmailDTO
    UserEmailDTO toUserEmailDTO(User user);

    //---UserRegistrationDTO
    User fromUserRegistrationDTO(UserRegistrationDTO userRegistrationDTO);
    UserRegistrationDTO toUserRegistrationDTO(User user);

    //---UserProfileDTO
    UserProfileDTO toUserProfileDTO(User user);
    User fromUserProfileDTO(UserProfileDTO userProfileDTO);

    //---UserAdminDTO
    UserAdminDTO toUserAdminDTO(User user);
    List<UserAdminDTO> toUserAdminDTOList(List<User> users);
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target="username", ignore = true)
    @Mapping(target="activationCode", ignore = true)
    void saveUserFromUserAdminDTO(UserAdminDTO userAdminDTO, @MappingTarget User user);

    //---UserAuthDTO
    UserAuthDTO toUserAuthDTO(User user);

    //---JwtUser
    JwtUser toJwtUser(User user);

    //---User
    void fromUserToUser(User source, @MappingTarget User target);



}
