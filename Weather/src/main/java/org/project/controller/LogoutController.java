package org.project.controller;

import org.project.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

    private final CookieService cookieService;
    
    @Autowired
    public LogoutController(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        cookieService.removeSessionCookie(response);
        return "redirect:/login";
    }
} 