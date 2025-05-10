package io.dev.lecture.infra.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "lecture")
@Getter
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
}
