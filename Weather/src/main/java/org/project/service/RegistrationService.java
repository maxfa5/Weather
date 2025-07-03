package org.project.service;

import org.springframework.stereotype.Service;
import org.project.repository.UserRepository;
import org.project.model.UserModel;
import org.project.exceptions.UserAlreadyExistsException;
import org.project.exceptions.ValidationException;

@Service
public class RegisterationService {
    
    private final UserRepository userRepository;

    public RegisterationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String login, String password) throws UserAlreadyExistsException, ValidationException {
        validateUser(login, password);

        UserModel user = new UserModel();
        user.setLogin(login);
        user.setPassword(password);
        userRepository.save(user);
    }

    private boolean isUserExists(String login) {
        try {
            if (userRepository.findByLogin(login).isPresent()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void validateUser(String login, String password) throws UserAlreadyExistsException, ValidationException {
        if (isUserExists(login)) {
            throw new UserAlreadyExistsException("Пользователь с логином '" + login + "' уже существует");
        }
        if (login.isEmpty() || password.isEmpty()) {
            throw new ValidationException("Логин и пароль не могут быть пустыми");
        }
        if (login.length() < 3 || password.length() < 3) {
            throw new ValidationException("Логин и пароль должны содержать минимум 3 символа");
        }
    }
}
