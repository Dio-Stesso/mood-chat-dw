package com.kirei.webchat.service.impl;

import com.kirei.webchat.model.User;
import com.kirei.webchat.repository.UserRepository;
import com.kirei.webchat.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository,
                           PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can`t find User by id " + id));
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return repository.findByUsernameOrEmail(username, email);
    }

    @Override
    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can`t delete User by id " + id)));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByResetPasswordId(String id) {
        return repository.findByResetPasswordId(id)
                .orElseThrow(() ->
                        new RuntimeException("Can`t find user by reset password id " + id));
    }

    @Override
    public User updatePassword(User user, String newPassword) {
        user.setResetPasswordId(null);
        user.setPassword(encoder.encode(newPassword));
        return repository.save(user);
    }
}
