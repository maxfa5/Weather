package org.project.controller;

import java.util.Optional;
import java.util.UUID;

import org.project.model.SessionModel;
import org.project.service.CookieService;
import org.project.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

@Controller
public class ProfileController {

    private final CookieService cookieService;
    private final SessionService sessionService;
    @Autowired
    public ProfileController(CookieService cookieService, SessionService sessionService) {
        this.cookieService = cookieService;
        this.sessionService = sessionService;
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        Optional<UUID> sessionId = cookieService.getSessionId(request);
        if (sessionId.isEmpty()) {
            return "redirect:/login";
        }
        System.out.println(sessionId.get().toString());
        SessionModel session = sessionService.getSession(sessionId.get());
        model.addAttribute("username", session.getUser().getLogin());
        return "profile";
    }

}
