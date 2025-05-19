package io.dev.lecture.infra.entity;

import io.dev.lecture.domain.model.Registration;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registration")
@Getter
@NoArgsConstructor
public class RegistrationEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registartion_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity schedule;

    public RegistrationEntity(UserEntity user, ScheduleEntity schedule) {
        this.user = user;
        this.schedule = schedule;
        user.getRegistrations().add(this);
        schedule.getRegistrations().add(this);
    }

    public Registration toRegistration() {
        return new Registration(id, user.getId(), schedule.getId());
    }
}
