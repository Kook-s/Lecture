package io.dev.lecture.infra.entity;

import io.dev.lecture.domain.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class UserEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;

    @OneToMany(mappedBy = "user")
    private List<RegistrationEntity> registrations= new ArrayList<>();

    public User toUser() {
        return new User(id, username, getCreatedAt(), getUpdatedAt());
    }

}
