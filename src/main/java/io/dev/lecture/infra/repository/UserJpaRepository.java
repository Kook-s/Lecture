package io.dev.lecture.infra.repository;

import io.dev.lecture.infra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
