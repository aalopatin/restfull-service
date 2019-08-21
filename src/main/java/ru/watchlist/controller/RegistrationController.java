package ru.watchlist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.UserDTO;
import ru.watchlist.mapper.UserMapper;
import ru.watchlist.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping
    public @ResponseBody String registration(@Valid @RequestBody UserDTO userDTO,
                                Errors errors) throws JsonProcessingException {

        String json = "";

        if(errors.hasErrors()) {

            List<Map<String, String>> errorMessages = new ArrayList<Map<String, String>>();

            List<ObjectError> allErrors= errors.getAllErrors();

            for (ObjectError error: allErrors) {
                errorMessages.add(new HashMap<String, String>() {{
                    put("error", error.getDefaultMessage());
                }});
            }

            Map<String, List> dataErrors = new HashMap<>();

            dataErrors.put("errors", errorMessages);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(dataErrors);

        } else {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(userMapper.toUserDTO(userService.createUser(userDTO)));
        }

        return json;

    }
}
