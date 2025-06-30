package org.project.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.project.model.SessionModel;
import org.project.model.UserModel;

public interface SessionRepository extends JpaRepository<SessionModel, UUID> {
    Optional<SessionModel> findById(UUID id);

    Optional<SessionModel> findByUser(UserModel user);
}
