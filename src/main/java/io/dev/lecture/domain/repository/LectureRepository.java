package io.dev.lecture.domain.repository;

import io.dev.lecture.domain.model.Lecture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureRepository {

    Optional<Lecture> findById(long lectureId);
    Optional<Lecture> findAvailableLectureById(Long lectureId);
    List<Lecture> findAvailableTimeLectureById(LocalDateTime date);
    void increaseLecture(long scheduleId);
    List<Lecture> findAllByIds(List<Long> lectureIds);
}
