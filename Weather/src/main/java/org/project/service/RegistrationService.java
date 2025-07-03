package org.project.service;

import org.springframework.stereotype.Service;
import org.project.repository.UserRepository;
import org.project.model.UserModel;
import org.project.exceptions.UserAlreadyExistsException;
import org.project.exceptions.ValidationException;

@Service
public class RegistrationService {
    
    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
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
            // Логирование ошибки при проверке существования пользователя
            // В продакшене следует использовать логгер вместо System.out
            System.err.println("Ошибка при проверке существования пользователя: " + e.getMessage());
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
        if (login.length() < 3 || password.length() < 6) {
            throw new ValidationException("Логин должен содержать минимум 3 символа, пароль - минимум 6 символов");
        }
    }
}
