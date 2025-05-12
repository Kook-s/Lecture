package io.dev.lecture.infra.Entity;

import io.dev.lecture.domain.model.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
public class UserEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String username;

    @OneToMany(mappedBy = "user")
    private List<RegistrationEntity> Registration = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(String username,LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.username = username;
    }

    public UserEntity(Long id, String username, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.username = username;
    }

    public User toUser() {
        return new User(id, username, getCreatedAt(), getUpdatedAt());
    }
}
