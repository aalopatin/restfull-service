package ru.watchlist.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.user.UserProfileDTO;
import ru.watchlist.dto.user.UserRegistrationDTO;
import ru.watchlist.mapper.UserMapper;
import ru.watchlist.rest.exception.ValidationErrorsException;
import ru.watchlist.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<UserRegistrationDTO> registration(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO,
                                       Errors errors) throws Exception {

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
