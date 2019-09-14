package ru.watchlist.service;

import ru.watchlist.domain.user.User;
import ru.watchlist.rest.exception.EntityNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User createUser(User user);
    User saveUser(User user);
    boolean activateUser(String code);
    boolean checkIDToken(Long id, HttpServletRequest request);


    User changeEmail(Long id, String Email) throws EntityNotFoundException;
    User changePassword(Long id, String password) throws EntityNotFoundException;

    //UserRepository
    List<User> getAll();
    User findByUsername(String username);
    User findById(Long id);


}
