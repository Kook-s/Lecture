package io.dev.lecture.infra.Entity;

import jakarta.persistence.*;
import lombok.Getter;

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

}
