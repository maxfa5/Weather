package org.project.service;

import org.springframework.stereotype.Service;
import org.project.repository.UserRepository;
import org.project.exceptions.UserNotFoundException;
import org.project.exceptions.InvalidCredentialsException;

import java.util.UUID;

import org.project.model.UserModel;
import org.project.service.SessionService;
import org.project.service.PasswordEncoder;

@Service
public class AuthService {
    
    private final SessionService sessionService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, SessionService sessionService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.passwordEncoder = passwordEncoder;
    }

    public UUID auth(String login, String password) throws UserNotFoundException, InvalidCredentialsException {
        UserModel user = userRepository.findByLogin(login).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с логином '" + login + "' не найден");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Неверный пароль");
        }
        UUID sessionId = sessionService.createSession(user);
        return sessionId;
    }

    private boolean isUserExists(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

}
