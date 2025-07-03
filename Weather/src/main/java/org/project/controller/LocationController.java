package org.project.controller;

import org.project.DTO.WeatherDataDTO;
import org.project.model.LocationModel;
import org.project.service.LocationService;
import org.project.service.WeatherService;
import org.project.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;
    private final CookieService cookieService;
    private final WeatherService weatherService;
    @Autowired
    public LocationController(LocationService locationService, CookieService cookieService, WeatherService weatherService) {
        this.locationService = locationService;
        this.cookieService = cookieService;
        this.weatherService = weatherService;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addLocation(
            @RequestParam String name,
            HttpServletRequest request) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            int userId = cookieService.getUserIdFromSession(request);
                      
            LocationModel location = new LocationModel();
            WeatherDataDTO weatherData = weatherService.getWeather(name);
            location.setName(name);
            location.setLatitude(Double.parseDouble(weatherData.getCoord().getLat()));
            location.setLongitude(Double.parseDouble(weatherData.getCoord().getLon()));
            location.setUserId(userId);
            
            LocationModel savedLocation = locationService.addLocation(location);
            
            response.put("success", true);
            response.put("message", "Локация успешно добавлена");
            response.put("location", savedLocation);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message",  e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteLocation(
            @PathVariable int id,
            HttpServletRequest request) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            int userId = cookieService.getUserIdFromSession(request);
            locationService.deleteLocation(id, userId);
            
            response.put("success", true);
            response.put("message", "Локация успешно удалена");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Ошибка при удалении локации: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
} 