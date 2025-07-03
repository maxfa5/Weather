package org.project.controller;

import org.project.service.RegistrationService;
import org.project.exceptions.UserAlreadyExistsException;
import org.project.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final RegistrationService registerService;
    
    @Autowired
    public RegisterController(RegistrationService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String login, 
                          @RequestParam String password, 
                          @RequestParam String confirmPassword, 
                          Model model) {
        if (login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            model.addAttribute("error", "Пожалуйста, заполните все поля");
            return "auth/register";
        }
        
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Пароли не совпадают");
            return "auth/register";
        }
        
        try {
            registerService.register(login, password);
            return "redirect:/login?success=true";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }
    }
} 