package org.project.DTO;

import lombok.Data;

@Data
public class WeatherDataDTO {
    private String city;
    private String latitude;
    private String longitude;
    private String weatherData;


    public WeatherDataDTO(String city, String latitude, String longitude, String weatherData) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weatherData = weatherData;
    }

    
    
}
