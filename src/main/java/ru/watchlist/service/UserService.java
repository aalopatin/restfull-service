package ru.watchlist.service;

import ru.watchlist.domain.user.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User saveUser(User user);
    boolean activateUser(String code);

    //UserRepository
    List<User> getAll();
    User findByUsername(String username);
    User findById(Long id);

}
