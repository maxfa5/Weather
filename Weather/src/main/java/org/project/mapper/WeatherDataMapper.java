package org.project.mapper;

import org.project.DTO.WeatherDataDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherDataMapper {
    public static WeatherDataDTO toWeatherDataDTO(String weatherData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(weatherData, WeatherDataDTO.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
