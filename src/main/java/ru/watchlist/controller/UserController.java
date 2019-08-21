package ru.watchlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.UserDTO;
import ru.watchlist.mapper.UserMapper;
import ru.watchlist.repository.UserRepository;
import ru.watchlist.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @GetMapping
    public List<UserDTO> getAllUsers() {

        return userMapper.toUserDTOList(userRepository.findAll());
    }

    @GetMapping("{id}")
    public UserDTO getUser(@PathVariable("id") User user) {

        return userMapper.toUserDTO(user);
    }

//    @PostMapping
//    public UserDTO createUser(@RequestBody UserDTO userDTO) {
//
//        return userMapper.toUserDTO(userService.createUser(userDTO));
//
//    }

    @PutMapping
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {

        return userMapper.toUserDTO(userService.saveUser(userDTO));

    }

}
