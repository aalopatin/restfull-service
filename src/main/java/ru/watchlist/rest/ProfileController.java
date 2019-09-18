package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.user.UserEmailDTO;
import ru.watchlist.dto.user.UserPasswordDTO;
import ru.watchlist.dto.user.UserProfileDTO;
import ru.watchlist.mapper.UserMapper;
import ru.watchlist.rest.exception.AccessDeniedProfileException;
import ru.watchlist.rest.exception.ValidationErrorsException;
import ru.watchlist.security.jwt.JwtTokenProvider;
import ru.watchlist.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/{id}")
    public UserProfileDTO profileBasic(@PathVariable(value = "id") User user) {

        return userMapper.toUserProfileDTO(user);
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<Object> changeEmail(
            HttpServletRequest request,
            @PathVariable Long id,
            @RequestBody @Valid UserEmailDTO userEmailDTO,
            Errors errors) throws Exception {

        if(userService.checkIDToken(id, request)) {
            throw new AccessDeniedProfileException("Вы не можете редактировать пользователя с указанным id!");
        }

        if(errors.hasErrors()) {
            throw new ValidationErrorsException(errors);
        }

        userService.changeEmail(id, userEmailDTO.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Object> changePassword(
            HttpServletRequest request,
            @PathVariable Long id,
            @RequestBody @Valid UserPasswordDTO userPasswordDTO,
            Errors errors) throws Exception {

        if(userService.checkIDToken(id, request)) {
            throw new AccessDeniedProfileException("Вы не можете редактировать пользователя с указанным id!");
        }

        if(errors.hasErrors()) {
            throw new ValidationErrorsException(errors);
        }

        userService.changePassword(id, userPasswordDTO.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
