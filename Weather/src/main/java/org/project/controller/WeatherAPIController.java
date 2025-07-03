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
import org.project.service.SessionService;
import org.project.service.WeatherService;
import org.project.service.CookieService;
import org.project.model.LocationModel;
import org.project.model.SessionModel;
import org.project.DTO.WeatherDataDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

@Controller
public class WeatherAPIController {
    private final WeatherService weatherService;
    private final LocationService locationService;
    private final CookieService cookieService;
    private final SessionService sessionService;

    @Autowired
    WeatherAPIController(WeatherService weatherService,SessionService sessionService, LocationService locationService, CookieService cookieService) {
        this.weatherService = weatherService;
        this.sessionService = sessionService;
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
            return "auth/login";
        }
        return "weather";
    }

    @GetMapping("/locations/count")
    @ResponseBody
    public int getCountLocations(HttpServletRequest request) {
        int userId = cookieService.getUserIdFromSession(request);
        return locationService.getCountLocations(userId);
    }

    @GetMapping("/weather")
    @ResponseBody
    public Map<String, Object> getWeatherForLocation(@RequestParam String city, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            WeatherDataDTO weatherData = weatherService.getWeather(city);
            int userId = cookieService.getUserIdFromSession(request);
            Optional<SessionModel> session = cookieService.getSession(request);
            if (session.isEmpty()) {
                response.put("success", false);
                response.put("message", "Не удалось получить данные о погоде для города: " + city);
                return response;
            }
            if (weatherData != null) {
                try {
                response.put("success", true);
                response.put("weatherStatus", weatherData.getWeather().get(0).getMain());
                response.put("weatherDescription", weatherData.getWeather().get(0).getDescription());
                if (session.get().getUser().getTypeOfDegrees().equals("C")) {
                    response.put("weatherTemperature", Math.round((Double.parseDouble(weatherData.getMain().getTemp()) - 273.15) )); //TODO service
                } else {
                    response.put("weatherTemperature", weatherData.getMain().getTemp());
                }
                response.put("weatherHumidity", weatherData.getMain().getHumidity());
                response.put("weatherWindSpeed", weatherData.getWind().getSpeed());
                response.put("weatherWindDirection", weatherData.getWind().getDeg());
                // response.put("weatherClouds", weatherData.getClouds().getAll());
                response.put("city", city);
                } catch (Exception e) {
                    response.put("success", false);
                    response.put("message", "Не удалось получить данные о погоде для города: " + city);
                }
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
