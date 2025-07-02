package org.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.project.service.LocationService;
import org.project.service.WeatherService;
import org.project.service.CookieService;
import org.project.model.LocationModel;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
public class WeatherAPIController {
    private final WeatherService weatherService;
    private final LocationService locationService;
    private final CookieService cookieService;
    
    @Autowired
    WeatherAPIController(WeatherService weatherService, LocationService locationService, CookieService cookieService) {
        this.weatherService = weatherService;
        this.locationService = locationService;
        this.cookieService = cookieService;
    }

    @GetMapping("/locations")
    public String getLocations(Model model, HttpServletRequest request) {
        try {
            int userId = cookieService.getUserIdFromSession(request);
            List<LocationModel> locations = locationService.getLocations(userId);
            model.addAttribute("locations", locations);
            model.addAttribute("userId", userId);
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при получении локаций: " + e.getMessage());
        }
        return "weather";
    }

    @GetMapping("/weather")
    @ResponseBody
    public Map<String, Object> getWeatherForLocation(@RequestParam String city) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String weatherData = weatherService.getWeather(city);
            if (weatherData != null) {
                response.put("success", true);
                response.put("weatherData", weatherData);
                response.put("city", city);
            } else {
                response.put("success", false);
                response.put("message", "Не удалось получить данные о погоде для города: " + city);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Ошибка при получении погоды: " + e.getMessage());
        }
        
        return response;
    }
}
