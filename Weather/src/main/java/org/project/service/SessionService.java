package org.project.service;

import org.springframework.stereotype.Service;
import org.project.repository.SessionRepository;
import org.project.model.SessionModel;
import org.project.model.UserModel;
import java.util.UUID;
import java.time.LocalDateTime;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

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
}  
