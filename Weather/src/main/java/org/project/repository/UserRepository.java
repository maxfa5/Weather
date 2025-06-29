package org.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.project.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByLogin(String login);    
}
