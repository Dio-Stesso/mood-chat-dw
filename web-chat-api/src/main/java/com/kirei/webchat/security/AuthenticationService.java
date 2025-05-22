package com.kirei.webchat.security;

import com.kirei.webchat.exception.AuthenticationException;
import com.kirei.webchat.model.User;

public interface AuthenticationService {
    User register(User user) throws AuthenticationException;

    User login(String login, String password) throws AuthenticationException;
}
