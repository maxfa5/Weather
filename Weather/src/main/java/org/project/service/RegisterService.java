package org.project.service;

import org.springframework.stereotype.Service;
import org.project.repository.UserRepository;
import org.project.model.UserModel;

@Service
public class RegisterService {
    
    private final UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String login, String password) {
        validateUser(login, password);

        UserModel user = new UserModel();
        user.setLogin(login);
        user.setPassword(password);
        userRepository.save(user);
    }

    private boolean isUserExists(String login) {
        try {
            userRepository.findByLogin(login);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void validateUser(String login, String password) {
        if (!isUserExists(login)) {
            throw new RuntimeException("User already exists");
        }
        if (login.isEmpty() || password.isEmpty()) {
            throw new RuntimeException("Login and password cannot be empty");
        }
        if (login.length() < 3 || password.length() < 3) {
            throw new RuntimeException("Login and password must be at least 3 characters long");
        }
    }
}
