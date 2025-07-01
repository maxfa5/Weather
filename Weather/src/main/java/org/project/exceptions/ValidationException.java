package org.project.exceptions;

/**
 * Исключение, выбрасываемое при ошибках валидации данных
 */
public class ValidationException extends WeatherAppException {
    
    public ValidationException() {
        super("Ошибка валидации данных");
    }
    
    public ValidationException(String message) {
        super(message);
    }
    
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String getErrorCode() {
        return "VALIDATION_ERROR";
    }
    
    @Override
    public String getErrorType() {
        return "VALIDATION_ERROR";
    }
} 