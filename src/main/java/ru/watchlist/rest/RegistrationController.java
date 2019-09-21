package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.CaptchaResponseDTO;
import ru.watchlist.dto.user.UserRegistrationDTO;
import ru.watchlist.mapper.UserMapper;
import ru.watchlist.rest.exception.ReCaptchaException;
import ru.watchlist.rest.exception.ValidationErrorsException;
import ru.watchlist.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {



    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/registration")
    public ResponseEntity<UserRegistrationDTO> registration(
            @Valid @RequestBody UserRegistrationDTO userRegistrationDTO,
            Errors errors) throws ValidationErrorsException {

        if(errors.hasErrors()) {
            throw new ValidationErrorsException(errors);
        }

        User user = userMapper.fromUserRegistrationDTO(userRegistrationDTO);
        user = userService.createUser(user);
        userRegistrationDTO = userMapper.toUserRegistrationDTO(user);

        return new ResponseEntity<>(userRegistrationDTO, HttpStatus.OK);

    }

    @PostMapping("/activate/{code}")
    public  Map<String, Boolean> activate(@PathVariable String code) {

        Map<String, Boolean> response = new HashMap<>();

        response.put("result", userService.activateUser(code));

        return response;

    }
}
