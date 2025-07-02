package org.project.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.project.model.SessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class CookieService {

    private final SessionService sessionService;
    @Autowired
    public CookieService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    private static final String SESSION_COOKIE_NAME = "sessionId";
    private static final int SESSION_COOKIE_MAX_AGE = 3600; // 1 час
    private static final String COOKIE_PATH = "/";


    public void createSessionCookie(HttpServletResponse response, UUID sessionId) {
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId.toString());
        cookie.setMaxAge(SESSION_COOKIE_MAX_AGE);
        cookie.setPath(COOKIE_PATH);
        cookie.setHttpOnly(true); // Защита от XSS
        cookie.setSecure(false); // Установить true для HTTPS
        response.addCookie(cookie);
    }
    public Optional<SessionModel> getSession(HttpServletRequest request) {
        return getSessionId(request)
                .map(sessionId -> {
                    try {
                        return sessionService.getSession(sessionId);
                    } catch (Exception e) {
                        return null;
                    }
                });
    }

    public Optional<UUID> getSessionId(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> SESSION_COOKIE_NAME.equals(cookie.getName()))
                .findFirst()
                .map(cookie -> {
                    try {
                        return UUID.fromString(cookie.getValue());
                    } catch (IllegalArgumentException e) {
                        return null;
                    }
                });
    }

    public int getUserIdFromSession(HttpServletRequest request) {
        return getSession(request).orElse(null).getUser().getId();
    }

    public void removeSessionCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, "");
        cookie.setMaxAge(0);
        cookie.setPath(COOKIE_PATH);
        response.addCookie(cookie);
    }


    public boolean hasValidSession(HttpServletRequest request) {
        return getSessionId(request).isPresent();
    }
} 