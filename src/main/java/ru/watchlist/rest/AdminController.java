package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.User;
import ru.watchlist.dto.UserAdminDTO;
import ru.watchlist.mapper.UserMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/users")
    public ResponseEntity<List<UserAdminDTO>> getUsers(){

        List<User> users = userService.getAll();

        return new ResponseEntity<>(userMapper.toUserAdminDTOList(users), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserAdminDTO> getUser(@PathVariable Long id) throws EntityNotFoundException {

        User user = userService.findById(id);

        return new ResponseEntity<>(userMapper.toUserAdminDTO(user), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> saveUser(@PathVariable Long id, @RequestBody UserAdminDTO userAdminDTO
    ) throws EntityNotFoundException {

        User user = userService.findById(id);

        userMapper.saveUserFromUserAdminDTO(userAdminDTO, user);

        userService.saveUser(user);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/users/{id}/activationCode")
    public ResponseEntity<Object> setActivationCode(@PathVariable Long id) throws EntityNotFoundException {
        User user = userService.findById(id);

        user = userService.changeActivationCode(user);

        Map<String, String> activationCode = new HashMap<>();
        activationCode.put("activationCode", user.getActivationCode());

        return new ResponseEntity<>(activationCode, HttpStatus.OK);
    }

}