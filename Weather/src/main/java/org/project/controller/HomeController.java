package org.project.controller;

import org.project.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final WeatherService weatherService;

    @Autowired
    public HomeController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String home() {
        // Перенаправление на страницу входа
        return "redirect:/login";
    }
} 