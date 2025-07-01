package org.project.exceptions;

/**
 * Исключение, выбрасываемое при неверных учетных данных пользователя
 */
public class InvalidCredentialsException extends WeatherAppException {
    
    public InvalidCredentialsException() {
        super("Неверный логин или пароль");
    }
    
    public InvalidCredentialsException(String message) {
        super(message);
    }
    
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String getErrorCode() {
        return "INVALID_CREDENTIALS";
    }
    
    @Override
    public String getErrorType() {
        return "AUTHENTICATION_ERROR";
    }
} 