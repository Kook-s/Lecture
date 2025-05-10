package io.dev.lecture.infra.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Lecture extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "lecture_id")
    private Long id;
    private String title;

    @OneToMany(mappedBy = "lecture")
    private List<Registration> registrations = new ArrayList<>();

    @OneToMany(mappedBy = "lecture")
    private List<LectureSchedule> lectureSchedules = new ArrayList<>();
}
