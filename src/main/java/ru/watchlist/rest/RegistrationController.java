package ru.watchlist.rest;

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
import ru.watchlist.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/registration")
    public @ResponseBody String registration(@Valid @RequestBody UserDTO userDTO,
                                Errors errors) throws JsonProcessingException {

        String json;

        if(errors.hasErrors()) {

            List<Map<String, String>> errorMessages = new ArrayList<>();

            List<ObjectError> allErrors= errors.getAllErrors();

            for (ObjectError error: allErrors) {
                errorMessages.add(new HashMap<>() {{
                    put("error", error.getDefaultMessage());
                }});
            }

            Map<String, List> dataErrors = new HashMap<>();

            dataErrors.put("errors", errorMessages);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(dataErrors);

        } else {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

            User user = userMapper.fromUserDTO(userDTO);
            user = userService.createUser(user);
            userDTO = userMapper.toUserDTO(user);

            json = ow.writeValueAsString(userDTO);
        }

        return json;

    }

    @PostMapping("/activate/{code}")
    public  Map<String, Boolean> activate(@PathVariable String code) {

        Map<String, Boolean> response = new HashMap<>();

        response.put("result", userService.activateUser(code));

        return response;

    }
}
