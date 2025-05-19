package io.dev.lecture.infra.repository;

import io.dev.lecture.infra.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
}
