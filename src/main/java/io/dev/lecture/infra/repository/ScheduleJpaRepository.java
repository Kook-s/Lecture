package io.dev.lecture.infra.repository;

import io.dev.lecture.infra.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpaRepository extends JpaRepository<ScheduleEntity, Long> {
}
