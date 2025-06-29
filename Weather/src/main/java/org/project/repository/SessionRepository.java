package org.project.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.project.model.SessionModel;

public interface SessionRepository extends JpaRepository<SessionModel, UUID> {
    Optional<SessionModel> findById(UUID id);
}
