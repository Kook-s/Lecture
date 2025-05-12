package io.dev.lecture.infra.Entity;

import io.dev.lecture.domain.model.Registration;
import io.dev.lecture.domain.model.User;
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

    public RegistrationEntity() {
    }

    public RegistrationEntity(UserEntity user, LectureEntity lecture) {
        this.user = user;
        this.lecture = lecture;

        user.getRegistration().add(this);
        lecture.getRegistrations().add(this);
    }

    public Registration toRegistration() {

        return new Registration(
                this.id,
                this.user.getId(),
                this.lecture.getId()
        );
    }
}
