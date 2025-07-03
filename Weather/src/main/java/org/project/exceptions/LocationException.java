package org.project.exceptions;

/**
 * Исключение, выбрасываемое при ошибках работы с локациями
 */
public class LocationException extends WeatherAppException {
    
    public LocationException() {
        super("Ошибка работы с локацией");
    }
    
    public LocationException(String message) {
        super(message);
    }
    
    public LocationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String getErrorCode() {
        return "LOCATION_ERROR";
    }
    
    @Override
    public String getErrorType() {
        return "LOCATION_ERROR";
    }
}