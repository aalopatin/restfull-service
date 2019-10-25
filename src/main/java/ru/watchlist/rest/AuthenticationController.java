package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.user.User;
import ru.watchlist.dto.AuthenticationRequestDTO;
import ru.watchlist.dto.AuthenticationResponseDTO;
import ru.watchlist.dto.user.UserAuthDTO;
import ru.watchlist.mapper.UserMapper;
import ru.watchlist.security.jwt.JwtTokenProvider;
import ru.watchlist.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO requestDTO){
        try {
            String username = requestDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDTO.getPassword()));
            User user = userService.findByUsername(username);

            if(user == null) {
                throw new UsernameNotFoundException("user with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO();
            responseDTO.setUsername(username);
            responseDTO.setToken(token);

            return ResponseEntity.ok(responseDTO);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("/user")
    public ResponseEntity user(HttpServletRequest request) {

        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);

        User user = userService.findByUsername(username);
        Map<String, UserAuthDTO> response = new HashMap<>();
        response.put("user", userMapper.toUserAuthDTO(user));

        return ResponseEntity.ok(response);

    }

}
