package org.project.service;

import org.springframework.stereotype.Service;
import org.project.repository.UserRepository;

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

    public UUID auth(String login, String password) {
        UserModel user = userRepository.findByLogin(login);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        UUID sessionId = sessionService.createSession(user);
        return sessionId;
    }

    private boolean isUserExists(String login) {
        return userRepository.findByLogin(login) != null;
    }

}
