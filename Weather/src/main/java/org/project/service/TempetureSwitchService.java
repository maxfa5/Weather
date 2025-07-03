package org.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;

import org.project.model.SessionModel;
import org.project.service.CookieService;

@Service
public class TempetureSwitchService {
    private final SessionService sessionService;
    private final CookieService cookieService;
    @Autowired
    public TempetureSwitchService(SessionService sessionService, CookieService cookieService) {
        this.sessionService = sessionService;
        this.cookieService = cookieService;
    }
    public String switchDegree(HttpServletRequest request) {
        Optional<UUID> sessionId = cookieService.getSessionId(request);
        if (sessionId.isEmpty()) {
            throw new RuntimeException("Session not found");
        }
        SessionModel session = sessionService.getSession(sessionId.get());
        if (session.getUser().getTypeOfDegrees().equals("C")) {
            session.getUser().setTypeOfDegrees("F");
        } else {
            session.getUser().setTypeOfDegrees("C");
        }
        sessionService.updateSession(session);
        return session.getUser().getTypeOfDegrees();
    }
}
