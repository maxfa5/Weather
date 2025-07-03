package org.project.controller;

import java.util.Optional;
import java.util.UUID;

import org.project.model.SessionModel;
import org.project.service.CookieService;
import org.project.service.SessionService;
import org.project.service.TempetureSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    private final CookieService cookieService;
    private final SessionService sessionService;
    private final TempetureSwitchService tempetureSwitchService;
        @Autowired
    public ProfileController(CookieService cookieService, SessionService sessionService, TempetureSwitchService tempetureSwitchService) {
        this.cookieService = cookieService;
        this.sessionService = sessionService;
        this.tempetureSwitchService = tempetureSwitchService;
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        Optional<UUID> sessionId = cookieService.getSessionId(request);
        if (sessionId.isEmpty()) {
            return "redirect:/login";
        }
        SessionModel session = sessionService.getSession(sessionId.get());
        model.addAttribute("username", session.getUser().getLogin());
        model.addAttribute("typeOfDegrees", session.getUser().getTypeOfDegrees());
        return "profile";
    }

    @PutMapping("/profile/degree")
    public String changeDegree(Model model, HttpServletRequest request) {
        Optional<UUID> sessionId = cookieService.getSessionId(request);
        if (sessionId.isEmpty()) {
            return "redirect:/login";
        }
        tempetureSwitchService.switchDegree(request);
        return "redirect:/profile";
    }

}
