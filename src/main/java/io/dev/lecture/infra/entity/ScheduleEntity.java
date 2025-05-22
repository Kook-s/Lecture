package io.dev.lecture.infra.entity;

import io.dev.lecture.domain.model.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedule")
@Getter
@NoArgsConstructor
public class ScheduleEntity extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    private final int maxCapacity = 30;
    private int currentCapacity = 0;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @OneToMany(mappedBy = "schedule")
    private List<RegistrationEntity> registrations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private LectureEntity lecture;

    public Schedule toSchedule() {
        return new Schedule(id, maxCapacity, currentCapacity, startAt, endAt, lecture.toLecture());
    }

    public void increaseCapacity() {
        if(currentCapacity < maxCapacity) {
            this.currentCapacity++;
        }else {
            throw new IllegalArgumentException("정원 초과했습니다.");
        }
    }
}
