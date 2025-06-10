package io.dev.lecture.domain.repository;

import io.dev.lecture.domain.model.Schedule;
import io.dev.lecture.infra.entity.ScheduleEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureRepository {

    Optional<Schedule> findById(long id);
    List<Schedule> findAvailableById(LocalDateTime date);
    List<Schedule> findAllLectures(List<Long> lectureIds);
    int increaseLecture(long scheduleId);
}
