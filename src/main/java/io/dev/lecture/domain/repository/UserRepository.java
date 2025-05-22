package io.dev.lecture.domain.repository;

import io.dev.lecture.domain.model.User;
import io.dev.lecture.infra.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(long userId);
}
