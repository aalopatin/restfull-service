package ru.watchlist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.watchlist.domain.user.Role;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.UserDTO;
import ru.watchlist.mapper.UserMapper;
import ru.watchlist.repository.UserRepository;
import ru.watchlist.service.MailSender;
import ru.watchlist.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

//@Service
//public class UserServiceImpl implements UserService {

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if(user == null) {
//
//            throw new UsernameNotFoundException("Ошибка авторизации!");
//
//        }
//
//        return user;
//    }

    @Override
    public User createUser(User user) {

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User createdUser = userRepository.save(user);

        sendMessage(createdUser);

        return createdUser;
    }

    @Override
    public User saveUser(User user) {

        User userFromDB = userRepository.findById(user.getId()).orElseThrow();
        userMapper.fromUserToUser(userFromDB, user);
        userRepository.save(userFromDB);
        return userFromDB;

    }

    @Override
    public boolean activateUser(String code) {

        User user = userRepository.findByActivationCode(code);

        if(user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepository.save(user);

        return true;

    }

    //UserRepository


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    //Service methods
    private void sendMessage(User user){

        if (!StringUtils.isEmpty(user.getEmail())) {

            Context ctx = new Context();

            ctx.setVariable("name", user.getUsername());
            ctx.setVariable("activationCode", user.getActivationCode());

            String htmlMessage = templateEngine.process("email/activationCode", ctx);

            mailSender.send(user.getEmail(), "The Watch List: активация электронной почты", htmlMessage);

        }

    }

}
