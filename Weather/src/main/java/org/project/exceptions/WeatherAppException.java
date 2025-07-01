package org.project.exceptions;

/**
 * Базовый класс для всех исключений приложения Weather App
 */
public abstract class WeatherAppException extends Exception {
    
    public WeatherAppException() {
        super();
    }
    
    public WeatherAppException(String message) {
        super(message);
    }
    
    public WeatherAppException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Получить код ошибки для данного типа исключения
     * @return код ошибки
     */
    public abstract String getErrorCode();
    
    /**
     * Получить тип ошибки
     * @return тип ошибки
     */
    public abstract String getErrorType();
} 