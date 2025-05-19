package io.dev.lecture.infra.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Registration> registrations= new ArrayList<>();

    @Builder
    public User(String name) {
        this.username = name;
    }

}
