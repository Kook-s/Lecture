package io.dev.lecture.infra.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity(name = "schedule")
@Getter
public class LectureScheduleEntity extends  BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    private final int maxCapacity = 30;
    private int currentCapacity = 0;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private LectureEntity lecture;

    public LectureScheduleEntity() {
    }

    public LectureScheduleEntity(int currentCapacity, LocalDateTime startTime, LectureEntity lecture) {
        this.currentCapacity = currentCapacity;
        this.startTime = startTime;
        this.endTime = startTime.plusHours(1);

        this.lecture = lecture;
        lecture.getLectureSchedules().add(this);
    }

    public void increaseCapacity() {
        if (this.currentCapacity > maxCapacity) {
            throw new IllegalArgumentException("정원 초과했습니다.");
        } else {
            this.currentCapacity++;
        }
    }

    public void decreaseCapacity() {
        if (this.currentCapacity < 0) {
            throw new IllegalArgumentException("수강 인원이 0명 입니다..");
        } else {
            this.currentCapacity--;
        }
    }
}
