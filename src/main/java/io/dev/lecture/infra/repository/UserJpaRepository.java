package io.dev.lecture.infra.repository;

import io.dev.lecture.infra.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
