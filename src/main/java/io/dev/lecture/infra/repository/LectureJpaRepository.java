package io.dev.lecture.infra.repository;

import io.dev.lecture.domain.model.Schedule;
import io.dev.lecture.infra.entity.LectureEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
}
