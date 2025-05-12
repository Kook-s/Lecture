package io.dev.lecture.infra.repository;

import io.dev.lecture.infra.Entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationJpaRepository extends JpaRepository<RegistrationEntity, Long> {
}
