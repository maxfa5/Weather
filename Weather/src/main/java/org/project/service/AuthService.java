package org.project.service;

import org.springframework.stereotype.Service;
import org.project.repository.UserRepository;
import org.project.exceptions.UserNotFoundException;
import org.project.exceptions.InvalidCredentialsException;

import java.util.UUID;

import org.project.model.UserModel;
import org.project.service.SessionService;

@Service
public class AuthService {
    private final SessionService sessionService;
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository, SessionService sessionService) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
    }

    public UUID auth(String login, String password) throws UserNotFoundException, InvalidCredentialsException {
        UserModel user = userRepository.findByLogin(login).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с логином '" + login + "' не найден");
        }
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Неверный пароль");
        }
        UUID sessionId = sessionService.createSession(user);
        return sessionId;
    }

    private boolean isUserExists(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

}
