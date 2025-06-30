package org.project.controller;

import java.util.UUID;

import org.project.service.AuthService;
import org.project.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private final AuthService authService;
    private final CookieService cookieService;
    
    @Autowired
    public LoginController(AuthService authService, CookieService cookieService) {
        this.authService = authService;
        this.cookieService = cookieService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login, 
                       @RequestParam String password, 
                       Model model,
                       HttpServletResponse response) {
        if (login.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Пожалуйста, заполните все поля");
            return "auth/login";
        }
        
        try {
            UUID sessionId = authService.auth(login, password);
            cookieService.createSessionCookie(response, sessionId);
            return "redirect:/dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
} 