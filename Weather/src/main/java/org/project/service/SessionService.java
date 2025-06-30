package org.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.project.repository.SessionRepository;
import org.project.model.SessionModel;
import org.project.model.UserModel;
import java.util.UUID;
import java.time.LocalDateTime;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public UUID createSession(UserModel user) {
        SessionModel session = new SessionModel();
        session.setId(UUID.randomUUID());
        session.setUser(user);
        session.setCreatedAt(LocalDateTime.now());
        sessionRepository.save(session);
        return session.getId();
    }
    public void deleteSession(UUID sessionId) {
        sessionRepository.deleteById(sessionId);
    }
    public SessionModel getSession(UUID sessionId) {
        if (sessionRepository.findById(sessionId).isPresent()) {
            return sessionRepository.findById(sessionId).get();
        }
        throw new RuntimeException("Session not found");
    }
    public SessionModel getSessionByUser(UserModel user) {
        if (sessionRepository.findByUser(user).isPresent()) {
            return sessionRepository.findByUser(user).get();
        } else {
            throw new RuntimeException("Session not found");
        }
    }
}  
