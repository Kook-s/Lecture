package io.dev.lecture.infra.Entity;

import io.dev.lecture.domain.model.Lecture;
import io.dev.lecture.domain.model.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "lecture")
@Getter
@ToString
public class LectureEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "lecture_id")
    private Long id;
    private String title;

    @OneToMany(mappedBy = "lecture")
    private List<RegistrationEntity> registrations = new ArrayList<>();

    @OneToMany(mappedBy = "lecture")
    private List<LectureScheduleEntity> lectureSchedules = new ArrayList<>();

    public LectureEntity() {}

    public LectureEntity( String title, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.title = title;
    }

    public Lecture toLectureOnly() {
        return new Lecture(
                this.id,
                this.title,
                getCreatedAt(),
                getUpdatedAt(),
                null
        );
    }

    public Lecture toLecture() {
        List<Schedule> scheduleModels = this.lectureSchedules.stream()
                .map(schedule -> new Schedule(
                        schedule.getId(),
                        schedule.getMaxCapacity(),
                        schedule.getCurrentCapacity(),
                        schedule.getStartTime(),
                        schedule.getEndTime(),
                        this.id
                ))
                .toList();

        return new Lecture(
                this.id,
                this.title,
                getCreatedAt(),
                getUpdatedAt(),
                scheduleModels
        );
    }
}
