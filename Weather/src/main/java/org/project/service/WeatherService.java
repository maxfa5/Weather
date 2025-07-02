package org.project.service;

import org.project.DTO.WeatherDataDTO;
import org.project.mapper.WeatherDataMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

@Service
public class WeatherService { 
    @Value("${weather.api.key}")
    private String API_KEY;
    private final WeatherDataMapper weatherDataMapper = new WeatherDataMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public WeatherDataDTO getWeather(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            WeatherDataDTO weatherData = WeatherDataMapper.toWeatherDataDTO(response.body());

            return weatherData;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
