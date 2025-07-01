package org.project.exceptions;

/**
 * Исключение, выбрасываемое при ошибках работы с сессией
 */
public class SessionException extends WeatherAppException {
    
    public SessionException() {
        super("Ошибка работы с сессией");
    }
    
    public SessionException(String message) {
        super(message);
    }
    
    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String getErrorCode() {
        return "SESSION_ERROR";
    }
    
    @Override
    public String getErrorType() {
        return "SESSION_ERROR";
    }
} 