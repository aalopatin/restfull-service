package ru.watchlist.service;

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

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null) {

            throw new UsernameNotFoundException("Ошибка авторизации!");

        }

        return user;
    }

    public User createUser(UserDTO userDTO) {
        User user = userMapper.fromUserDTO(userDTO);

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        sendMessage(user);

        return user;
    }

    public User saveUser(UserDTO userDTO) {

        User user = userRepository.findById(userDTO.getId()).orElseThrow();
        userMapper.fromUserDTOToUser(userDTO, user);
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
