package org.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "sessions")
public class SessionModel {

    @Id
    UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserModel user;

    @Column(name = "created_at")
    LocalDateTime createdAt;
}
