package io.dev.lecture.infra.Entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity(name = "registration")
@Getter
public class RegistrationEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "registration_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private LectureEntity lecture;
}
