package com.kirei.webchat.controller;

import com.kirei.webchat.exception.AuthenticationException;
import com.kirei.webchat.model.Role;
import com.kirei.webchat.model.User;
import com.kirei.webchat.model.dto.UserLoginDto;
import com.kirei.webchat.model.dto.UserRegistrationDto;
import com.kirei.webchat.model.dto.UserResponseDto;
import com.kirei.webchat.security.AuthenticationService;
import com.kirei.webchat.security.jwt.JwtTokenProvider;
import com.kirei.webchat.service.MailSender;
import com.kirei.webchat.service.mapper.UserMapper;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailSender mailSender;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserMapper userMapper,
                                    JwtTokenProvider jwtTokenProvider,
                                    MailSender mailSender) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.mailSender = mailSender;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationDto userRequestDto)
            throws AuthenticationException {
        User user = userMapper.mapToModel(userRequestDto);
        authenticationService.register(user);
//        mailSender.send(userRequestDto.getEmail(),
//                "Thank you for registering!",
//                String.format("Login: %s\nPassword: %s\n\nAll the best!",
//                        userRequestDto.getUsername(),
//                        userRequestDto.getPassword()));
        return userMapper.mapToDto(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto) {
        try {
            User user = authenticationService.login(userLoginDto.getUsername(),
                    userLoginDto.getPassword());
            String token = jwtTokenProvider.createToken(user.getUsername(),
                    user.getRoles().stream()
                            .map(Role::getRoleName)
                            .collect(Collectors.toList()));
            return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(Map.of("errorMessage", e.getMessage()),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
