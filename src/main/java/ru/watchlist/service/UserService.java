package ru.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
import ru.watchlist.domain.enums.Role;
import ru.watchlist.domain.User;
import ru.watchlist.repository.UserRepository;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.security.jwt.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
public class UserService{

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

//    @Autowired
//    private TemplateEngine templateEngine;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User createdUser = userRepository.save(user);

        sendActivationCode(createdUser);

        return createdUser;
    }

    public User saveUser(User user) {

        userRepository.save(user);
        return user;

    }

    public boolean activateUser(String code) {

        User user = userRepository.findByActivationCode(code);

        if(user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepository.save(user);

        return true;

    }

    public boolean checkIDToken(Long id, HttpServletRequest request) {

        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);

        User userFromJWT = userRepository.findByUsername(username);

        return userFromJWT==null || !id.equals(userFromJWT.getId());

    }

    public User changeActivationCode(User user) {

        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        sendActivationCode(user);

        return user;
    }

    public User changeEmail(Long id, String email) throws EntityNotFoundException {

        User user = userRepository.findById(id).orElse(null);

        if (user==null) {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }

        user.setEmail(email);
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        sendActivationCode(user);

        return user;

    }

    public User changePassword(Long id, String password) throws EntityNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user==null) {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    //UserRepository

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) throws EntityNotFoundException {

        User user = userRepository.findById(id).orElse(null);

        if (user==null) {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }

        return user;
    }

    //Service methods

    private void sendActivationCode(User user){

        if (!StringUtils.isEmpty(user.getEmail())) {
//
//            Context ctx = new Context();
//
//            ctx.setVariable("name", user.getUsername());
//            ctx.setVariable("activationCode", user.getActivationCode());
//
//            String htmlMessage = templateEngine.process("email/activationCode", ctx);
//
//            mailSender.send(user.getEmail(), "The Watch List: активация электронной почты", htmlMessage);

        }

    }

}
