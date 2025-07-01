package org.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.project.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByLogin(String login);    
}
