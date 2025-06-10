package io.dev.lecture.infra.repository;

import io.dev.lecture.domain.model.User;
import io.dev.lecture.infra.entity.UserEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<UserEntity> findById(long userId);
}
