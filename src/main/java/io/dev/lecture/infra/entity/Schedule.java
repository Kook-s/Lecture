package io.dev.lecture.infra.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedule")
@Getter
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    private final int maxCapacity = 30;
    private int currentCapacity = 0;

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "schedule")
    private List<Registration> registrations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}
