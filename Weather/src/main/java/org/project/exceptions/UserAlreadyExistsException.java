package org.project.exceptions;

/**
 * Исключение, выбрасываемое при попытке создать пользователя, который уже существует
 */
public class UserAlreadyExistsException extends WeatherAppException {
    
    public UserAlreadyExistsException() {
        super("Пользователь с таким логином уже существует");
    }
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }
    
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String getErrorCode() {
        return "USER_ALREADY_EXISTS";
    }
    
    @Override
    public String getErrorType() {
        return "VALIDATION_ERROR";
    }
} 