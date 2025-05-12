package io.dev.lecture.infra.repository;

import io.dev.lecture.infra.Entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
}
