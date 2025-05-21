package io.dev.lecture.infra.repository;

import io.dev.lecture.infra.entity.RegistrationEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface RegistrationJpaRepository extends JpaRepository<RegistrationEntity, Long> {
    List<RegistrationEntity> findByUserId(Long userId);

    Optional<RegistrationEntity> findByUserIdAndScheduleId(Long userId, Long scheduleId);
}
