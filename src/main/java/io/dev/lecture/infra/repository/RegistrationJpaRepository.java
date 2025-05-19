package io.dev.lecture.infra.repository;

import io.dev.lecture.infra.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationJpaRepository extends JpaRepository<RegistrationEntity, Long> {
    List<RegistrationEntity> findByUserId(Long userId);
}
