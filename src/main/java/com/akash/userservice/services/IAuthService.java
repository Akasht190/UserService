package com.akash.userservice.services;

import com.akash.userservice.models.User;

public interface IAuthService {
    User signUp(String email, String password);

    String login(String email, String password);

    boolean validateToken(String token, Long userId);
}
