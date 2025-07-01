package org.project.exceptions;

/**
 * Исключение, выбрасываемое когда пользователь не найден
 */
public class UserNotFoundException extends WeatherAppException {
    
    public UserNotFoundException() {
        super("Пользователь не найден");
    }
    
    public UserNotFoundException(String message) {
        super(message);
    }
    
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String getErrorCode() {
        return "USER_NOT_FOUND";
    }
    
    @Override
    public String getErrorType() {
        return "NOT_FOUND_ERROR";
    }
} 