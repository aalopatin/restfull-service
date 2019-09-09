package ru.watchlist.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.UserDTO;
import ru.watchlist.dto.UserProfileDTO;
import ru.watchlist.mapper.UserMapper;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/{id}")
    public UserProfileDTO profileBasic(@PathVariable(value = "id") User user) {
        return userMapper.toUserProfileDTO(user);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity saveProfile(@PathVariable(value = "id") User user, @Valid @RequestBody UserDTO userDTO,
                                      Errors errors) {

        if(errors.hasErrors()) {

            Set<Map<String, String>> errorMessages = new HashSet<>();

            List<ObjectError> allErrors= errors.getAllErrors();

            for (ObjectError error: allErrors) {
                errorMessages.add(new HashMap<>() {{
                    put("error", error.getDefaultMessage());
                }});
            }

            Map<String, Set> dataErrors = new HashMap<>();

            dataErrors.put("errors", errorMessages);

            return ResponseEntity.badRequest(dataErrors);

        } else {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

            User user = userMapper.fromUserDTO(userDTO);
            user = userService.createUser(user);
            userDTO = userMapper.toUserDTO(user);

            json = ow.writeValueAsString(userDTO);
        }


    }
}
