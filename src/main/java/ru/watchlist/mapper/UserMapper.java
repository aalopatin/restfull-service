package ru.watchlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.watchlist.domain.user.Role;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.UserAuthDTO;
import ru.watchlist.dto.user.UserProfileDTO;
import ru.watchlist.dto.user.UserEmailDTO;
import ru.watchlist.dto.user.UserRegistrationDTO;
import ru.watchlist.security.jwt.JwtUser;

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

    //---UserAuthDTO
    UserAuthDTO toUserAuthDTO(User user);

    //---JwtUser
    JwtUser toJwtUser(User user);

    //---User
    void fromUserToUser(User source, @MappingTarget User target);



}
