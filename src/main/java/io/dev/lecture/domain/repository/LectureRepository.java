package io.dev.lecture.domain.repository;

import io.dev.lecture.domain.model.Lecture;

import java.util.Optional;

public interface LectureRepository {

    Optional<Lecture> findById(long lectureId);
    Optional<Lecture> findAvailableLectureById(Long lectureId);
    void increaseLecture(long scheduleId);

}
