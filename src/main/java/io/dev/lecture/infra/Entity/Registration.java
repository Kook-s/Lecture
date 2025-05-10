package io.dev.lecture.infra.Entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Registration extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "registration_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}
