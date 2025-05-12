package io.dev.lecture.infra.repository;

import io.dev.lecture.infra.Entity.LectureScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureScheduleJpaRepository extends JpaRepository<LectureScheduleEntity, Long> {
}
